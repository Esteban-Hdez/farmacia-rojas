package web;

import datos.ProductoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvEliminarVenta", urlPatterns = {"/SvEliminarVenta"})
public class SvEliminarVenta extends HttpServlet{
    
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
        int idVenta = Integer.parseInt(request.getParameter("idVenta"));
        
        System.out.println("Id venta obtenido: " + idVenta);
        
        //boolean eliminado = false;
        //eliminado = new ProductoDAO().eliminarProducto(idProducto);
        
        HttpSession session = request.getSession();
        
        if(true){
            System.out.println("Venta eliminada con exito.");
            session.setAttribute("mensaje", "La venta fue eliminada exitosamente.");
            session.setAttribute("style", "success");
        } else {
            System.out.println("Error al eliminar la venta.");
            session.setAttribute("mensaje", "Error al eliminar la venta.");
            session.setAttribute("style", "danger");
        }
        response.sendRedirect("/Farmaciav1/SvVenta");    
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}