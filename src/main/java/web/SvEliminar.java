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

@WebServlet(name = "SvEliminar", urlPatterns = {"/SvEliminar"})
public class SvEliminar extends HttpServlet{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        
        boolean eliminado = false;
        eliminado = new ProductoDAO().eliminarProducto(idProducto);
        
        HttpSession session = request.getSession();
        
        if(eliminado){
            System.out.println("Producto eliminado con exito.");
            
            session.setAttribute("mensaje", "El producto fue eliminado exitosamente.");
            session.setAttribute("style", "success");
        } else {
            System.out.println("Error al eliminar.");
            session.setAttribute("mensaje", "Error al eliminar el producto.");
            session.setAttribute("style", "danger");
        }
        response.sendRedirect("/Farmaciav1/ServletControlador1");    
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
