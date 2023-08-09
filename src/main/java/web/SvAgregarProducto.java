package web;

import Dominio.Producto;
import datos.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvAgregarProducto", urlPatterns = {"/SvAgregarProducto"})
public class SvAgregarProducto extends HttpServlet {

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
        
        if(insertado){
            System.out.println("Producto insertado con exito");
        } else System.out.println("Error al insertar");
        
        response.sendRedirect("agregarProducto.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
