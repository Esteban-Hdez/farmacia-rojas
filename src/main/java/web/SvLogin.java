package web;

import Dominio.Producto;
import Dominio.Usuario;
import datos.ProductoDAO;
import datos.UsuarioDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String correo_electronico = request.getParameter("usuario");
        String contrasenna = request.getParameter("contrasenna");

        System.out.println("Usuario: " + correo_electronico);
        System.out.println("Contraseña: " + contrasenna);

        Usuario usuario = new UsuarioDAO().login(correo_electronico, contrasenna);
        System.out.println(usuario);
        HttpSession session = request.getSession();
        
        List<Producto> productos = new ProductoDAO().obtenerEstadisticasProductos();

        if (usuario != null) {
            session.setAttribute("usuario", usuario);
            session.setAttribute("productosEstadisticos", productos);
            response.sendRedirect("inicio.jsp");
        } else {
            session.setAttribute("mensaje", "Usuario y/o contraseña incorrectos");
            session.setAttribute("style", "danger");
            response.sendRedirect("login.jsp");
        }
    }

}
