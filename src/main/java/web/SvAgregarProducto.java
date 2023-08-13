package web;

import Dominio.Producto;
import datos.ProductoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvAgregarProducto", urlPatterns = {"/SvAgregarProducto"})
public class SvAgregarProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigoBarras = request.getParameter("codigoBarras");
        String nombreProducto = request.getParameter("nombreProducto");
        String fechaIngreso = request.getParameter("fechaIngreso");
        String fechaVencimiento = request.getParameter("fechaVencimiento");
        int tipoMedicamento = Integer.parseInt(request.getParameter("tipoMedicamento"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double precio = Double.parseDouble(request.getParameter("precio"));
        
        Producto p = new Producto(codigoBarras, nombreProducto, fechaIngreso, fechaVencimiento, tipoMedicamento, cantidad, precio);
        System.out.println(p);
        
        boolean insertado = false;
        insertado = new ProductoDAO().insertarProducto(p);
        
        HttpSession session = request.getSession();
        
        if(insertado){
            System.out.println("Producto insertado con exito.");
            
            session.setAttribute("mensaje", "El producto <strong>" + p.getNombre() + "</strong> fue agregado exitosamente.");
            session.setAttribute("style", "success");
        } else {
            System.out.println("Error al insertar.");
            session.setAttribute("mensaje", "Error al agregar el producto.");
            session.setAttribute("style", "danger");
        }
        response.sendRedirect("agregarProducto.jsp");    
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
