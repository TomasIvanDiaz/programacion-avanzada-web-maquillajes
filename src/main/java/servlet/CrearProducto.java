package servlet;

import java.io.IOException;

import dao.CategoriaDAO;
import dao.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Producto;
import modelo.Usuario;

@WebServlet("/crearProducto")
public class CrearProducto extends HttpServlet {
	
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!validarAdmin(request, response)) return;

        //VERIFICAMOS USUARIO Y PASAMOS DIRECTAMENTE AL JSP (XQ NO HAY NADA QUE TRAER PARA MOSTRAR AL USUARIO)
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        
        request.setAttribute("categorias", categoriaDAO.listarCategorias());
        request.getRequestDispatcher("/vistas/crearProducto.jsp").forward(request, response);
    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!validarAdmin(request, response)) return;
        
        Producto producto = new Producto();
        
        producto.setNombre(request.getParameter("nombre"));
        producto.setDescripcion(request.getParameter("descripcion"));
        producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
        producto.setStock(Integer.parseInt(request.getParameter("stock")));
        producto.setActivo(request.getParameter("activo") != null);
        producto.setMarca(request.getParameter("marca"));
        producto.setImagen(request.getParameter("imagen"));
        producto.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));
        
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.insertarProducto(producto);
        
        response.sendRedirect("panelAdmin"); 
    }
	
}
