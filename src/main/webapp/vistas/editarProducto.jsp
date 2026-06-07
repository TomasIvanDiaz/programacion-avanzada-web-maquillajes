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

    <h1>Editar producto</h1>

    <form action="<%= request.getContextPath() %>/editarProducto" method="post">

        <input type="hidden" name="id" value="<%= producto.getId() %>">

        <label>Nombre:</label>
        <input type="text" name="nombre" value="<%= producto.getNombre() %>" required>

        <label>Descripción:</label>
        <textarea name="descripcion"><%= producto.getDescripcion() %></textarea>

        <label>Precio:</label>
        <input type="number" step="0.01" name="precio" value="<%= producto.getPrecio() %>" required>

        <label>Stock:</label>
        <input type="number" name="stock" value="<%= producto.getStock() %>" required>

        <label>Categoría:</label>
        <select name="idCategoria" required>
            <% for (Categoria categoria : categorias) { %>
                <option value="<%= categoria.getId() %>"
                    <%= categoria.getId() == producto.getIdCategoria() ? "selected" : "" %>>
                    <%= categoria.getNombre() %>
                </option>
            <% } %>
        </select>

        <label>
            <input type="checkbox" name="activo" <%= producto.isActivo() ? "checked" : "" %>>
            Activo
        </label>

        <button type="submit">Guardar cambios</button>
        <a href="<%= request.getContextPath() %>/panelAdmin">Cancelar</a>

    </form>

</body>
</html>