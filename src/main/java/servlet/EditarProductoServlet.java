package servlet;

import dao.CategoriaDAO;
import dao.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Producto;

import java.io.IOException;

@WebServlet("/editarProducto")
public class EditarProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        Producto producto = new Producto();

        producto.setId(Integer.parseInt(request.getParameter("id")));
        producto.setNombre(request.getParameter("nombre"));
        producto.setDescripcion(request.getParameter("descripcion"));
        producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
        producto.setStock(Integer.parseInt(request.getParameter("stock")));
        producto.setActivo(request.getParameter("activo") != null);
        producto.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));

        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.modificarProducto(producto);

        response.sendRedirect("panelAdmin");
    }
}