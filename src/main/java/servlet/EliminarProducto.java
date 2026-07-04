package servlet;

import java.io.IOException;

import dao.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;


@WebServlet("/eliminarProducto")
public class EliminarProducto extends HttpServlet {

    private boolean validarAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("usuario") == null) {
            response.sendRedirect("login");
            return false;
        }
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        if (!usuario.isEsAdmin()) {
            response.sendRedirect("catalogo");
            return false;
        }
        return true;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!validarAdmin(request, response)) return;

        int id = Integer.parseInt(request.getParameter("id"));
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.eliminarProducto(id);
        response.sendRedirect("panelAdmin");
    }

}
