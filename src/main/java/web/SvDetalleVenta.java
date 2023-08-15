package web;

import Dominio.DetalleVenta;
import Dominio.Venta;
import datos.VentaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

@WebServlet(name = "SvDetalleVenta", urlPatterns = {"/SvDetalleVenta"})
public class SvDetalleVenta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        System.out.println("index: "+index);
        Venta venta = new VentaDAO().buscarVentaPorId(index);
        List<DetalleVenta> detalleV = venta.getDetalles();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Usar una librería como Gson para convertir la lista de productos a JSON
        Gson gson = new Gson();
        String productosJson = gson.toJson(detalleV);
        
        System.out.println(index + ": " + productosJson);
        
        // Enviar la respuesta JSON al cliente
        try (PrintWriter out = response.getWriter()) {
            out.print(productosJson);
            out.flush();
        }
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
