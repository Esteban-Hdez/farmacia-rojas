package datos;

import Dominio.DetalleVenta;
import Dominio.Producto;
import Dominio.Venta;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    private static final String SQL_SELECT_VENTAS = "SELECT * FROM Venta";
    private static final String SQL_SELECT_VENTA_POR_ID = "SELECT * FROM Venta WHERE id_venta = ?";
    private static final String SQL_SELECT_VENTAS_Y_DETALLES = "SELECT\n"
            + "    v.id_venta,\n"
            + "    v.fecha_hora,\n"
            + "    v.total_venta,\n"
            + "    dv.cantidad_vendida,\n"
            + "    p.nombre AS nombre_producto,\n"
            + "    p.precio\n"
            + "FROM\n"
            + "    Venta v\n"
            + "JOIN\n"
            + "    Detalle_Venta dv ON v.id_venta = dv.id_venta\n"
            + "JOIN\n"
            + "    Producto p ON dv.id_producto = p.id_producto\n"
            + "ORDER BY\n"
            + "    v.id_venta;";

    // Obtener la fecha y hora actual del sistema
    LocalDateTime now = LocalDateTime.now();

    // Crear un formateador de fecha y hora
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Formatear la fecha y hora actual
    String formattedDateTime = now.format(formatter);

    public boolean agregarVentaConDetalle(Venta venta) throws SQLException {
        try (Connection conn = Conexion.getConnection()) {
            conn.setAutoCommit(false); // Desactivar el autocommit para manejar transacciones

            // Insertar la venta en la tabla Venta
            String ventaQuery = "INSERT INTO Venta (fecha_hora, total_venta) VALUES (?, ?)";
            try (PreparedStatement ventaStmt = conn.prepareStatement(ventaQuery, Statement.RETURN_GENERATED_KEYS)) {
                ventaStmt.setString(1, formattedDateTime);
                ventaStmt.setDouble(2, venta.getTotalVenta());
                ventaStmt.executeUpdate();

                try (ResultSet generatedKeys = ventaStmt.getGeneratedKeys()) {
                    int ventaId = 0;
                    if (generatedKeys.next()) {
                        ventaId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("No se pudo obtener el ID de la venta generada.");
                    }

                    // Insertar el detalle de venta en la tabla Detalle_Venta
                    String detalleQuery = "INSERT INTO Detalle_Venta (id_producto, id_venta, cantidad_vendida) VALUES (?, ?, ?)";
                    try (PreparedStatement detalleStmt = conn.prepareStatement(detalleQuery)) {
                        List<DetalleVenta> detalles = venta.getDetalles();
                        for (DetalleVenta detalle : detalles) {
                            detalleStmt.setInt(1, detalle.getProducto().getIdProducto());
                            detalleStmt.setInt(2, ventaId);
                            detalleStmt.setInt(3, detalle.getCantidadVendida());
                            detalleStmt.addBatch();
                        }

                        detalleStmt.executeBatch();

                        // Disminuir la cantidad en el inventario de productos
                        String updateProductoQuery = "UPDATE Producto SET cantidad = cantidad - ? WHERE id_producto = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateProductoQuery)) {
                            for (DetalleVenta detalle : detalles) {
                                updateStmt.setInt(1, detalle.getCantidadVendida());
                                updateStmt.setInt(2, detalle.getProducto().getIdProducto());
                                updateStmt.addBatch();
                            }
                            updateStmt.executeBatch();
                        }
                    }

                    conn.commit(); // Confirmar la transacción
                    return true; // Indica que la operación se realizó con éxito
                } catch (SQLException e) {
                    conn.rollback(); // Revertir la transacción en caso de error
                    throw e;
                }
            }
        }
    }

    public List<Venta> obtenerVentasConDetalles() {
        List<Venta> ventas = new ArrayList<>();

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_SELECT_VENTAS_Y_DETALLES); ResultSet rs = ps.executeQuery()) {

            int currentVentaId = -1;
            Venta currentVenta = null;
            List<DetalleVenta> detalles = new ArrayList<>();

            while (rs.next()) {
                int idVenta = rs.getInt("id_venta");

                if (idVenta != currentVentaId) {
                    if (currentVenta != null) {
                        currentVenta.setDetalles(detalles);
                        ventas.add(currentVenta);
                    }

                    currentVenta = new Venta();
                    currentVenta.setIdVenta(idVenta);
                    currentVenta.setFechaVenta(rs.getString("fecha_hora"));
                    currentVenta.setTotalVenta(rs.getDouble("total_venta"));

                    detalles = new ArrayList<>();
                    currentVentaId = idVenta;
                }

                int cantidadVendida = rs.getInt("cantidad_vendida");
                String nombreProducto = rs.getString("nombre_producto");
                double precioProducto = rs.getDouble("precio");

                Producto producto = new Producto(nombreProducto, precioProducto);
                DetalleVenta detalle = new DetalleVenta(producto, cantidadVendida);
                detalles.add(detalle);
            }

            if (currentVenta != null) {
                currentVenta.setDetalles(detalles);
                ventas.add(currentVenta);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return ventas;
    }

    public List<Venta> listarTodasLasVentas() {
        List<Venta> ventas = new ArrayList<>();

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_SELECT_VENTAS); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idVenta = rs.getInt("id_venta");
                String fechaHora = rs.getString("fecha_hora");
                double totalVenta = rs.getDouble("total_venta");

                List<DetalleVenta> detalleVenta = new DetalleVentaDAO().obtenerDetalleVentaPorIdVenta(idVenta);

                Venta venta = new Venta(idVenta, fechaHora, totalVenta, detalleVenta);
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return ventas;
    }

    public Venta buscarVentaPorId(int index) {
        Venta venta = new Venta();

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_SELECT_VENTA_POR_ID)) {

            ps.setInt(1, index);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idVenta = rs.getInt("id_venta");
                    String fechaHora = rs.getString("fecha_hora");
                    double totalVenta = rs.getDouble("total_venta");

                    List<DetalleVenta> detalleVenta = new DetalleVentaDAO().obtenerDetalleVentaPorIdVenta(idVenta);

                    venta = new Venta(idVenta, fechaHora, totalVenta, detalleVenta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return venta;
    }

}
