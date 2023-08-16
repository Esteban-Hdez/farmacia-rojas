package datos;

import Dominio.TipoProducto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoProductoDAO {
    // Consultas SQL para la tabla Producto

    private static final String SQL_SELECT = "SELECT * FROM tipo_producto";

    // Métodos y lógica para acceder a la base de datos y ejecutar las consultas
    // Método para listar todos los productos
    public List<TipoProducto> listarTiposProductos() {
        List<TipoProducto> listaProductos = new ArrayList<>();

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_SELECT); ResultSet rs = ps.executeQuery()) {
            // Realizar operaciones con el ResultSet
            // Iterar a través de los resultados y agregar los productos a la lista
            while (rs.next()) {
                int idTipoProducto = rs.getInt("id_tipo_producto");
                String descripcion = rs.getString("descripcion");

                // Crear un objeto Producto y agregarlo a la lista
                TipoProducto tipoProducto = new TipoProducto(idTipoProducto, descripcion);
                listaProductos.add(tipoProducto);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return listaProductos;
    }
}
