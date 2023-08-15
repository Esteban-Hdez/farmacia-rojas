package datos;

import Dominio.Usuario;
import java.sql.*;

public class UsuarioDAO {

    private static final String SQL_SELECT_USUARIO = "SELECT * FROM Usuario WHERE correo_electronico = ? AND contrasena = ?";

    public Usuario login(String correo_electronico, String password) {
        Usuario usuario = null;

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_SELECT_USUARIO)) {

            ps.setString(1, correo_electronico);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt("id_usuario");
                    String nombre = rs.getString("nombre");
                    String apellidoPaterno = rs.getString("apellido_paterno");
                    String apellidoMaterno = rs.getString("apellido_materno");
                    correo_electronico = rs.getString("correo_electronico");
                    String fecha_registro = rs.getString("fecha_registro");

                    usuario = new Usuario(idUsuario, nombre, apellidoPaterno, apellidoMaterno, correo_electronico, fecha_registro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return usuario;
    }

}
