<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="modelo.Producto" %>
<%@ page import="modelo.Categoria" %>
<%@ page import="java.util.List" %>

<%
    Producto producto = (Producto) request.getAttribute("producto");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar producto</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>
<body>

    <h1 class="pagina-titulo">Editar producto</h1>

    <div class="pagina-contenido">
        <div class="contenido">

            <form action="<%= request.getContextPath() %>/editarProducto" method="post" class="formulario">

                <input type="hidden" name="id" value="<%= producto.getId() %>">

                <div>
                    <label>Nombre</label>
                    <input type="text" name="nombre" value="<%= producto.getNombre() %>" required>
                </div>

                <div>
                    <label>Descripción</label>
                    <textarea name="descripcion"><%= producto.getDescripcion() %></textarea>
                </div>

                <div>
                    <label>Precio</label>
                    <input type="number" step="0.01" name="precio" value="<%= producto.getPrecio() %>" required>
                </div>

                <div>
                    <label>Stock</label>
                    <input type="number" name="stock" value="<%= producto.getStock() %>" required>
                </div>

                <div>
                    <label>Marca</label>
                    <input type="text" name="marca" value="<%= producto.getMarca() != null ? producto.getMarca() : "" %>"> <!-- ES UNA FORMA DE ESCRIBIR UN IF AND ELSE -->
                </div>

                <div>
                    <label>Imagen (nombre del archivo)</label>
                    <input type="text" name="imagen" value="<%= producto.getImagen() != null ? producto.getImagen() : "" %>">
                </div>

                <div>
                    <label>Categoría</label>
                    <select name="idCategoria" required>
                        <% if (categorias != null) {
                            for (Categoria categoria : categorias) { %>
                                <option value="<%= categoria.getId() %>" <%= categoria.getId() == producto.getIdCategoria() ? "selected" : "" %>>
                                    <%= categoria.getNombre() %>
                                </option>
                        <% }} %>
                    </select>
                </div>

                <div>
                    <label>
                        <input type="checkbox" name="activo" <%= producto.isActivo() ? "checked" : "" %>>
                        Activo
                    </label>
                </div>

                <div class="formulario-acciones">
                    <button type="submit" class="btn btn-primario">Guardar cambios</button>
                    <a href="<%= request.getContextPath() %>/panelAdmin" class="btn">Cancelar</a>
                </div>

            </form>

        </div>
    </div>

    <div class="pagina-footer">
        <a href="<%= request.getContextPath() %>/logout" class="btn">Cerrar sesión</a>
    </div>

</body>
</html>
