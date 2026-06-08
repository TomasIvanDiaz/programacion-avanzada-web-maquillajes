package servlet;

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

import java.io.IOException;

@WebServlet("/editarProducto")
public class EditarProductoServlet extends HttpServlet {

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

        //BUSCAMOS EL ID DEL PRODUCTO AL CUAL APRETAMOS EDITAR
        int id = Integer.parseInt(request.getParameter("id"));

        ProductoDAO productoDAO = new ProductoDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        Producto producto = productoDAO.buscarPorId(id);

        request.setAttribute("producto", producto);
        request.setAttribute("categorias", categoriaDAO.listarCategorias());

        request.getRequestDispatcher("/vistas/editarProducto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!validarAdmin(request, response)) return;

        Producto producto = new Producto();

        //INSTANCIAMOS PRODUCTO, AGARRAMOS LO INGRESADO POR EL USUARIO, LO SETEAMOS EN EL OBJETO PRODUCTO QUE CREAMOS,
        //Y LUEGO APLICAMOS LA FUNCION DE MODIFICAR PRODUCTO
        
        producto.setId(Integer.parseInt(request.getParameter("id")));
        producto.setNombre(request.getParameter("nombre"));
        producto.setDescripcion(request.getParameter("descripcion"));
        producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
        producto.setStock(Integer.parseInt(request.getParameter("stock")));
        producto.setActivo(request.getParameter("activo") != null);
        producto.setMarca(request.getParameter("marca"));
        producto.setImagen(request.getParameter("imagen"));
        producto.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));

        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.modificarProducto(producto);

        response.sendRedirect("panelAdmin");
    }
}