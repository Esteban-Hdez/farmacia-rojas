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

    // Métodos y lógica para acceder a la base de datos y ejecutar las consultas
    // Método para listar todos los productos
    public List<Producto> listarTodosLosProductos() {
        List<Producto> listaProductos = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Inicializar la conexión y el PreparedStatement
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT_PRODUCTOS);
            rs = ps.executeQuery();

            // Iterar a través de los resultados y agregar los productos a la lista
            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String codigoBarras = rs.getString("codigo_barras");
                String nombre = rs.getString("nombre");
                Date fechaIngreso = rs.getDate("fecha_ingreso");
                Date fechaVencimiento = rs.getDate("fecha_vencimiento");
                String idTipoProducto = rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");

                // Crear un objeto Producto y agregarlo a la lista
                Producto producto = new Producto(idProducto, codigoBarras, nombre, fechaIngreso, fechaVencimiento, idTipoProducto, cantidad, precio);
                listaProductos.add(producto);
            }

        } catch (SQLException e) {
            // Manejar excepciones
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);

        }

        return listaProductos;
    }

    // Método para recuperar un producto por su id
    public Producto obtenerProductoPorId(Producto producto) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Inicializar la conexión y el PreparedStatement
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT_PRODUCTO_BY_ID);
            ps.setInt(1, producto.getIdProducto());
            rs = ps.executeQuery();

            // Verificar si se encontró un producto con el id proporcionado
            if (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String codigoBarras = rs.getString("codigo_barras");
                String nombre = rs.getString("nombre");
                Date fechaIngreso = rs.getDate("fecha_ingreso");
                Date fechaVencimiento = rs.getDate("fecha_vencimiento");
                String idTipoProducto = rs.getString("id_tipo_producto");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");

                // Crear un objeto Producto con la información obtenida
                producto = new Producto(idProducto, codigoBarras, nombre, fechaIngreso, fechaVencimiento, idTipoProducto, cantidad, precio);
            }

        } catch (SQLException e) {
            // Manejar excepciones
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }

        return producto;
    }

    // Método para insertar un nuevo producto
    public boolean insertarProducto(Producto producto) {
        boolean insertado = false;

        Connection conn = null;
        PreparedStatement ps = null;

        // Inicializar la conexión y el PreparedStatement
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT_PRODUCTO);

            // Establecer los valores de los parámetros en la consulta SQL
            ps.setString(1, producto.getCodigoBarras());
            ps.setString(2, producto.getNombre());
            ps.setDate(3, new java.sql.Date(producto.getFechaIngreso().getTime()));
            ps.setDate(4, new java.sql.Date(producto.getFechaVencimiento().getTime()));
            ps.setInt(5, producto.getIdTipo());
            ps.setInt(6, producto.getCantidad());
            ps.setDouble(7, producto.getPrecio());

            // Ejecutar la consulta
            int filasInsertadas = ps.executeUpdate();

            // Verificar si se insertó correctamente el producto
            if (filasInsertadas > 0) {
                insertado = true;
            }

        } catch (SQLException e) {
            // Manejar excepciones
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }

        return insertado;
    }

    // Método para actualizar un producto existente
    public boolean actualizarProducto(Producto producto) {
        boolean actualizado = false;

        Connection conn = null;
        PreparedStatement ps = null;

        // Inicializar la conexión y el PreparedStatement
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE_PRODUCTO);

            // Establecer los valores de los parámetros en la consulta SQL
            ps.setString(1, producto.getCodigoBarras());
            ps.setString(2, producto.getNombre());
            ps.setDate(3, new java.sql.Date(producto.getFechaIngreso().getTime()));
            ps.setDate(4, new java.sql.Date(producto.getFechaVencimiento().getTime()));
            ps.setInt(5, producto.getIdTipo());
            ps.setInt(6, producto.getCantidad());
            ps.setDouble(7, producto.getPrecio());
            ps.setInt(8, producto.getIdProducto()); // Establecer el idProducto para la condición WHERE

            // Ejecutar la consulta
            int filasActualizadas = ps.executeUpdate();

            // Verificar si se actualizó correctamente el producto
            if (filasActualizadas > 0) {
                actualizado = true;
            }

        } catch (SQLException e) {
            // Manejar excepciones
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }

        return actualizado;
    }

    // Método para eliminar un producto por su id
    public boolean eliminarProducto(int idProducto) {
        boolean eliminado = false;

        Connection conn = null;
        PreparedStatement ps = null;

        // Inicializar la conexión y el PreparedStatement
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE_PRODUCTO);

            // Establecer el valor del parámetro en la consulta SQL
            ps.setInt(1, idProducto);

            // Ejecutar la consulta
            int filasEliminadas = ps.executeUpdate();

            // Verificar si se eliminó correctamente el producto
            if (filasEliminadas > 0) {
                eliminado = true;
            }

        } catch (SQLException e) {
            // Manejar excepciones
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(ps);
            Conexion.close(conn);
        }

        return eliminado;
    }

}
