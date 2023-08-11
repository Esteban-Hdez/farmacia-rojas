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

@WebServlet(name = "SvEditar", urlPatterns = {"/SvEditar"})
public class SvEditar extends HttpServlet{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        List<TipoProducto> tiposProductos = new TipoProductoDAO().listarTiposProductos();
        
        int idProducto = Integer.parseInt(request.getParameter("id_producto"));
        Producto producto = new ProductoDAO().obtenerProductoPorId(idProducto);
        HttpSession session = request.getSession();
        session.setAttribute("productoEditar", producto);
        session.setAttribute("tiposProductos", tiposProductos);
        response.sendRedirect("editarProducto.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        String codigoBarras = request.getParameter("codigoBarras");
        String nombreProducto = request.getParameter("nombreProducto");
        String fechaIngreso = request.getParameter("fechaIngreso");
        String fechaVencimiento = request.getParameter("fechaVencimiento");
        int tipoMedicamento = Integer.parseInt(request.getParameter("tipoMedicamento"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double precio = Double.parseDouble(request.getParameter("precio"));
        
        Producto p = new Producto(idProducto, codigoBarras, nombreProducto, fechaIngreso, fechaVencimiento, tipoMedicamento, cantidad, precio);
        System.out.println(p);
        
        boolean actualizado = false;
        actualizado = new ProductoDAO().actualizarProducto(p);
        
        HttpSession session = request.getSession();
        
        if(actualizado){
            System.out.println("Producto actualizado con exito.");
            
            session.setAttribute("mensaje", "El producto fue actualizado exitosamente.");
            session.setAttribute("style", "success");
        } else {
            System.out.println("Error al actualizar.");
            session.setAttribute("mensaje", "Error al actualizar el producto.");
            session.setAttribute("style", "danger");
        }
        response.sendRedirect("/Farmaciav1/ServletControlador1");    
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
