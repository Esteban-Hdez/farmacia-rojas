
import Dominio.DetalleVenta;
import Dominio.Venta;
import com.google.gson.Gson;
import datos.DetalleVentaDAO;
import datos.VentaDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
        
        response.setContentType("text/plain");

        // Leer el JSON enviado desde JavaScript
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        
        // JSON de detalles de venta en un string
        String detalleVenta = stringBuilder.toString();

        // Ahora puedes manejar el JSON como prefieras
        System.out.println("JSON recibido: " + detalleVenta);
        
        HttpSession session = request.getSession();
        
        if(true){
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
