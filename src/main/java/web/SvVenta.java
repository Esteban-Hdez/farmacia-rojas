package web;

import Dominio.Venta;
import datos.VentaDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvVenta", urlPatterns = {"/SvVenta"})
public class SvVenta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Venta> ventas = new VentaDAO().listarTodasLasVentas();
        System.out.println(ventas);
        HttpSession misesion = request.getSession();
        misesion.setAttribute("ventas", ventas);
        
        response.sendRedirect("historialVentas.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
