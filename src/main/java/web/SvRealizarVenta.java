
import Dominio.DetalleVenta;
import Dominio.Producto;
import Dominio.Venta;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import datos.DetalleVentaDAO;
import datos.ProductoDAO;
import datos.VentaDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




@WebServlet(name = "SvRealizarVenta", urlPatterns = {"/SvRealizarVenta"})
public class SvRealizarVenta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JsonParser jsonParser = new JsonParser();
        
        response.setContentType("text/plain");

        // Leer el JSON enviado desde JavaScript
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        
        // JSON de detalles de venta en un string
        String jsonDetalleVenta = stringBuilder.toString();
        JsonObject jsonObject = jsonParser.parse(jsonDetalleVenta).getAsJsonObject();

        Venta venta = new Venta();
        venta.setDetalles(new ArrayList<>());

        venta.setTotalVenta(jsonObject.get("total").getAsDouble());
        
        JsonArray productosArray = jsonObject.getAsJsonArray("productos");
        for (int i = 0; i < productosArray.size(); i++) {
            JsonObject detalleObj = productosArray.get(i).getAsJsonObject();

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setCantidadVendida(detalleObj.get("cantidad").getAsInt());

            Producto producto = new Producto();
            producto.setIdProducto(detalleObj.get("id").getAsInt());
            producto.setCodigoBarras(detalleObj.get("codigo").getAsString());
            producto.setNombre(detalleObj.get("nombre").getAsString());
            producto.setPrecio(detalleObj.get("precio").getAsDouble());

            detalleVenta.setProducto(producto);
            venta.getDetalles().add(detalleVenta);
        }
        
        boolean exito = false;
        try {
            exito = new VentaDAO().agregarVentaConDetalle(venta);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        HttpSession session = request.getSession();
        
        if(exito){
            List<Producto> productos = new ProductoDAO().obtenerEstadisticasProductos();
            session.setAttribute("productosEstadisticos", productos);
            System.out.println("Venta registrada con exito.");
            
            session.setAttribute("mensaje", "Venta registrada con exito.");
            session.setAttribute("style", "success");
        } else {
            System.out.println("Error al realizar la venta.");
            session.setAttribute("mensaje", "Error al realizar la venta.");
            session.setAttribute("style", "danger");
        }
    }
}
