package datos;

import Dominio.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // Consultas SQL para la tabla Producto
    private static final String SQL_INSERT_PRODUCTO = "INSERT INTO Producto (codigo_barras, nombre, fecha_ingreso, fecha_vencimiento, id_tipo_producto, cantidad, precio) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_PRODUCTOS = "SELECT p.id_producto, p.codigo_barras, p.nombre, p.fecha_ingreso, p.fecha_vencimiento, tp.descripcion, p.cantidad, p.precio "
            + "FROM Producto p "
            + "INNER JOIN Tipo_Producto tp ON p.id_tipo_producto = tp.id_tipo_producto";
    private static final String SQL_SELECT_PRODUCTO_BY_ID = "SELECT * FROM Producto WHERE id_producto = ?";
    private static final String SQL_UPDATE_PRODUCTO = "UPDATE Producto SET codigo_barras = ?, nombre = ?, fecha_ingreso = ?, fecha_vencimiento = ?, id_tipo_producto = ?, cantidad = ?, precio = ? WHERE id_producto = ?";
    private static final String SQL_DELETE_PRODUCTO = "DELETE FROM Producto WHERE id_producto = ?";

    private static final String SQL_SELECT_PRODUCTOS_FILTRADOS = "SELECT * FROM Producto WHERE codigo_barras = ? OR nombre LIKE ?";

    private static final String SQL_PRODUCTO_MAS_VENDIDO = "SELECT p.id_producto, p.codigo_barras, p.nombre, SUM(dv.cantidad_vendida) AS cantidad, p.fecha_ingreso "
            + "FROM Producto p JOIN Detalle_Venta dv ON p.id_producto = dv.id_producto "
            + "GROUP BY p.id_producto, p.nombre ORDER BY cantidad DESC LIMIT 1";
    private static final String SQL_PRODUCTO_MAS_VENDIDO_MES = "SELECT p.id_producto, p.codigo_barras, p.nombre, SUM(dv.cantidad_vendida) AS cantidad, p.fecha_ingreso\n"
            + "FROM Producto p\n"
            + "JOIN Detalle_Venta dv ON p.id_producto = dv.id_producto\n"
            + "JOIN Venta v ON dv.id_venta = v.id_venta\n"
            + "WHERE MONTH(v.fecha_hora) = MONTH(CURDATE()) AND YEAR(v.fecha_hora) = YEAR(CURDATE())\n"
            + "GROUP BY p.id_producto\n"
            + "ORDER BY cantidad DESC\n"
            + "LIMIT 1;";
    private static final String SQL_PRODUCTO_MENOS_VENDIDO = "SELECT p.id_producto, p.codigo_barras, p.nombre, SUM(dv.cantidad_vendida) AS cantidad, p.fecha_ingreso "
            + "FROM Producto p JOIN Detalle_Venta dv ON p.id_producto = dv.id_producto "
            + "GROUP BY p.id_producto, p.nombre ORDER BY cantidad ASC LIMIT 1";
    private static final String SQL_PRODUCTO_MAS_TIEMPO_EN_STOCK = "SELECT * FROM Producto "
            + "ORDER BY DATEDIFF(NOW(), fecha_ingreso) DESC LIMIT 1";

    // Métodos y lógica para acceder a la base de datos y ejecutar las consultas
    // Método para listar todos los productos
    public List<Producto> listarTodosLosProductos() {
        List<Producto> listaProductos = new ArrayList<>();

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_SELECT_PRODUCTOS); ResultSet rs = ps.executeQuery()) {
            // Realizar operaciones con el ResultSet
            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String codigoBarras = rs.getString("codigo_barras");
                String nombre = rs.getString("nombre");
                String fechaIngreso = rs.getString("fecha_ingreso");
                String fechaVencimiento = rs.getString("fecha_vencimiento");
                String idTipoProducto = rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");

                // Crear un objeto Producto y agregarlo a la lista
                Producto producto = new Producto(idProducto, codigoBarras, nombre, fechaIngreso, fechaVencimiento, idTipoProducto, cantidad, precio);
                listaProductos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return listaProductos;
    }

    // Método para recuperar un producto por su id
    public Producto obtenerProductoPorId(int id) {
        Producto producto = null;

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_SELECT_PRODUCTO_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idProducto = rs.getInt("id_producto");
                    String codigoBarras = rs.getString("codigo_barras");
                    String nombre = rs.getString("nombre");
                    String fechaIngreso = rs.getString("fecha_ingreso");
                    String fechaVencimiento = rs.getString("fecha_vencimiento");
                    int idTipoProducto = rs.getInt("id_tipo_producto");
                    int cantidad = rs.getInt("cantidad");
                    double precio = rs.getDouble("precio");

                    producto = new Producto(idProducto, codigoBarras, nombre, fechaIngreso, fechaVencimiento, idTipoProducto, cantidad, precio);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return producto;
    }

    // Método para insertar un nuevo producto
    public boolean insertarProducto(Producto producto) {
        boolean insertado = false;

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_INSERT_PRODUCTO)) {

            ps.setString(1, producto.getCodigoBarras());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getFechaIngreso());
            ps.setString(4, producto.getFechaVencimiento());
            ps.setInt(5, producto.getIdTipo());
            ps.setInt(6, producto.getCantidad());
            ps.setDouble(7, producto.getPrecio());

            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas > 0) {
                insertado = true;
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return insertado;
    }

// Método para actualizar un producto existente
    public boolean actualizarProducto(Producto producto) {
        boolean actualizado = false;

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_PRODUCTO)) {

            ps.setString(1, producto.getCodigoBarras());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getFechaIngreso());
            ps.setString(4, producto.getFechaVencimiento());
            ps.setInt(5, producto.getIdTipo());
            ps.setInt(6, producto.getCantidad());
            ps.setDouble(7, producto.getPrecio());
            ps.setInt(8, producto.getIdProducto());

            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas > 0) {
                actualizado = true;
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return actualizado;
    }

    // Método para eliminar un producto por su id
    public boolean eliminarProducto(int idProducto) {
        boolean eliminado = false;

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_DELETE_PRODUCTO)) {

            ps.setInt(1, idProducto);

            int filasEliminadas = ps.executeUpdate();

            if (filasEliminadas > 0) {
                eliminado = true;
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return eliminado;
    }

    // Método para obtener productos filtrados por código de barras y nombre
    public List<Producto> listarProductosFiltrados(String codigoBarras, String nombre) {
        List<Producto> listaProductos = new ArrayList<>();

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_SELECT_PRODUCTOS_FILTRADOS)) {

            ps.setString(1, codigoBarras);
            ps.setString(2, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idProducto = rs.getInt("id_producto");
                    codigoBarras = rs.getString("codigo_barras");
                    nombre = rs.getString("nombre");
                    String fechaIngreso = rs.getString("fecha_ingreso");
                    String fechaVencimiento = rs.getString("fecha_vencimiento");
                    String idTipoProducto = rs.getString("descripcion");
                    int cantidad = rs.getInt("cantidad");
                    double precio = rs.getDouble("precio");

                    Producto producto = new Producto(idProducto, codigoBarras, nombre, fechaIngreso, fechaVencimiento, idTipoProducto, cantidad, precio);
                    listaProductos.add(producto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return listaProductos;
    }

//// Método para obtener estadísticas de productos
//    public List<Producto> obtenerEstadisticasProductos() {
//        List<Producto> estadisticasProductos = new ArrayList<>();
//
//        try (Connection conn = Conexion.getConnection(); 
//                PreparedStatement psMasVendido = conn.prepareStatement(SQL_PRODUCTO_MAS_VENDIDO); 
//                PreparedStatement psMasVendidoMes = conn.prepareStatement(SQL_PRODUCTO_MAS_VENDIDO_MES); 
//                PreparedStatement psMenosVendido = conn.prepareStatement(SQL_PRODUCTO_MENOS_VENDIDO); 
//                PreparedStatement psMasTiempoEnStock = conn.prepareStatement(SQL_PRODUCTO_MAS_TIEMPO_EN_STOCK)) {
//
//            estadisticasProductos.add(obtenerProductoDesdeResultSet(psMasVendido.executeQuery()));
//            estadisticasProductos.add(obtenerProductoDesdeResultSet(psMasVendidoMes.executeQuery()));
//            estadisticasProductos.add(obtenerProductoDesdeResultSet(psMenosVendido.executeQuery()));
//            estadisticasProductos.add(obtenerProductoDesdeResultSet(psMasTiempoEnStock.executeQuery()));
//
//        } catch (SQLException e) {
//            e.printStackTrace(System.out);
//        }
//
//        return estadisticasProductos;
//    }
//    
//// Método para crear un objeto Producto a partir de un ResultSet
//    private Producto obtenerProductoDesdeResultSet(ResultSet rs) throws SQLException {
//        int idProducto = rs.getInt("id_producto");
//        String codigoBarras = rs.getString("codigo_barras");
//        String nombre = rs.getString("nombre");
//        int cantidad = rs.getInt("cantidad"); //total vendido
//        String fecha_ingreso = rs.getString("fecha_ingreso");
//
//        return new Producto(idProducto, codigoBarras, nombre, cantidad, fecha_ingreso);
//    }
    // Método para obtener estadísticas de productos
    public List<Producto> obtenerEstadisticasProductos() {
        List<Producto> estadisticasProductos = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();

            // Consulta para el producto más vendido
            ps = conn.prepareStatement(SQL_PRODUCTO_MAS_VENDIDO);
            rs = ps.executeQuery();
            if (rs.next()) {
                Producto masVendido = crearProductoDesdeResultSet(rs);
                estadisticasProductos.add(masVendido);
            }

            // Consulta para el producto más vendido por mes
            ps = conn.prepareStatement(SQL_PRODUCTO_MAS_VENDIDO_MES);
            rs = ps.executeQuery();
            if (rs.next()) {
                Producto masVendidoMes = crearProductoDesdeResultSet(rs);
                estadisticasProductos.add(masVendidoMes);
            }

            // Consulta para el producto menos vendido
            ps = conn.prepareStatement(SQL_PRODUCTO_MENOS_VENDIDO);
            rs = ps.executeQuery();
            if (rs.next()) {
                Producto menosVendido = crearProductoDesdeResultSet(rs);
                estadisticasProductos.add(menosVendido);
            }

            // Consulta para el producto con más tiempo en stock
            ps = conn.prepareStatement(SQL_PRODUCTO_MAS_TIEMPO_EN_STOCK);
            rs = ps.executeQuery();
            if (rs.next()) {
                Producto masTiempoEnStock = crearProductoDesdeResultSet(rs);
                estadisticasProductos.add(masTiempoEnStock);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            if (rs != null) {
                Conexion.close(rs);
            }
            if (ps != null) {
                Conexion.close(ps);
            }
            if (conn != null) {
                Conexion.close(conn);
            }
        }

        return estadisticasProductos;
    }

    // Método para crear un objeto Producto a partir de un ResultSet
    private Producto crearProductoDesdeResultSet(ResultSet rs) throws SQLException {
        int idProducto = rs.getInt("id_producto");
        String codigoBarras = rs.getString("codigo_barras");
        String nombre = rs.getString("nombre");
        int cantidad = rs.getInt("cantidad"); //total vendido
        String fecha_ingreso = rs.getString("fecha_ingreso");

        return new Producto(idProducto, codigoBarras, nombre, cantidad, fecha_ingreso);
    }

}
