package servlet;

import dao.CategoriaDAO;
import dao.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Categoria;
import modelo.Producto;
import modelo.Usuario;

import java.io.IOException;
import java.util.List;

@WebServlet("/panelAdmin")
public class PanelAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	//VALIDAMOS PRIMERO QUE LA SESION SIGA ACTIVA
    	HttpSession sesion = request.getSession(false);
    	if (sesion == null || sesion.getAttribute("usuario") == null) {
    	    response.sendRedirect("login");
    	    return;
    	}
    	
    	//SI NO ES ADMIN, Y ES USUARIO, REDIRIGE AL CATALOGO
    	Usuario usuario = (Usuario) sesion.getAttribute("usuario");
    	if (!usuario.isEsAdmin()) {
    		response.sendRedirect("catalogo");
    	    return;
    	}
    	
    	//SIN HACER ESTO, CUALQUIERA CON LA URL PODRIA ENTRAR SIN LOGUEARSE (SIN HACER LO DE ARRIBA)

    	//CREAMOS OBJETOS DE PRODUCTO Y CATEGORIA, PEDIMOS LAS LISTAS Y SE LA PASAMOS AL JSP PARA QUE LO MUESTRE
    	//EN EL PANEL
    	
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.listarProductos();

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.listarCategorias();

        request.setAttribute("productos", productos);
        request.setAttribute("categorias", categorias);

        request.getRequestDispatcher("/vistas/panelAdmin.jsp").forward(request, response);
    }
}