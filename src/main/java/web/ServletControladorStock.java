package web;

import Dominio.Producto;
import Dominio.TipoProducto;
import datos.ProductoDAO;
import datos.TipoProductoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletControladorStock", urlPatterns = {"/ServletControladorStock"})
public class ServletControladorStock extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Producto> productos = new ProductoDAO().listarTodosLosProductos();
        //List<TipoProducto> tiposProductos = new TipoProductoDAO().listarTiposProductos();
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("productos", productos);
        //misesion.setAttribute("tiposProductos", tiposProductos);
        
        response.sendRedirect("stock.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
