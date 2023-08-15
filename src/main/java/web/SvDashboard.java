package web;

import Dominio.Producto;
import datos.ProductoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvDashboard", urlPatterns = {"/SvDashboard"})
public class SvDashboard extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Producto> productos = new ProductoDAO().obtenerEstadisticasProductos();
//        System.out.println("productos: "+productos);
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("productosEstadisticos", productos);
        response.sendRedirect("inicio.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
