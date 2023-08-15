package datos;

import Dominio.DetalleVenta;
import Dominio.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAO {

    private static final String SQL_SELECT_DETALLE_VENTA = "SELECT dv.cantidad_vendida, p.nombre AS nombre_producto, p.precio "
            + "FROM Detalle_Venta dv "
            + "JOIN Producto p ON dv.id_producto = p.id_producto "
            + "WHERE dv.id_venta = ?";

    public List<DetalleVenta> obtenerDetalleVentaPorIdVenta(int idVenta) {
        List<DetalleVenta> detalleVenta = new ArrayList<>();

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_SELECT_DETALLE_VENTA)) {
            ps.setInt(1, idVenta);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int cantidadVendida = rs.getInt("cantidad_vendida");
                    String nombreProducto = rs.getString("nombre_producto");
                    double precioProducto = rs.getDouble("precio");

                    Producto producto = new Producto(nombreProducto, precioProducto);
                    DetalleVenta detalle = new DetalleVenta(producto, cantidadVendida);
                    detalleVenta.add(detalle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return detalleVenta;
    }

}
