package web;

import Dominio.TipoProducto;
import datos.TipoProductoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvTipoProducto", urlPatterns = {"/SvTipoProducto"})
public class SvTipoProducto extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<TipoProducto> tiposProductos = new TipoProductoDAO().listarTiposProductos();
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("tiposProductos", tiposProductos);
        
        response.sendRedirect("agregarProducto.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
