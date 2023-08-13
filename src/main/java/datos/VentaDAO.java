package datos;

import Dominio.DetalleVenta;
import Dominio.Venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    private static final String SQL_SELECT_VENTAS = "SELECT * FROM Venta";
    
    public List<Venta> listarTodasLasVentas() {
        List<Venta> ventas = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(SQL_SELECT_VENTAS);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idVenta = rs.getInt("id_venta");
                java.sql.Timestamp fechaHora = rs.getTimestamp("fecha_hora");
                double totalVenta = rs.getDouble("total_venta");
                
                List<DetalleVenta> detalleVenta = new DetalleVentaDAO().obtenerDetalleVentaPorIdVenta(idVenta);

                Venta venta = new Venta(idVenta, fechaHora, totalVenta, detalleVenta);
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conn);
        }

        return ventas;
    }

}
