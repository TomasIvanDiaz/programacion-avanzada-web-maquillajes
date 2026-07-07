package servlet;

import dao.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Producto;
import modelo.Usuario;

import java.io.IOException;
import java.util.List;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false); //Busca una sesion ya existente

        if (sesion == null || sesion.getAttribute("usuario") == null) {
            response.sendRedirect("login");
            return;
        } //Verificar si el usuario está logueado

        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        if (usuario.isEsAdmin()) {
            response.sendRedirect("panelAdmin");
            return;
        }

        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.listarProductos();

        request.setAttribute("usuario", usuario);
        request.setAttribute("productos", productos);

        request.getRequestDispatcher("/vistas/catalogo.jsp").forward(request, response);
    }
}