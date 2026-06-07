<%@ page import="modelo.Producto" %>
<%@ page import="java.util.List" %>

<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel administrador</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>
<body>

    <h1>Panel administrador</h1>

    <a href="<%= request.getContextPath() %>/logout">Cerrar sesión</a>

    <h2>Productos</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Activo</th>
            <th>Categoría</th>
            <th>Acciones</th>
        </tr>

        <% if (productos != null) { %>
            <% for (Producto producto : productos) { %>
                <tr>
                    <td><%= producto.getId() %></td>
                    <td><%= producto.getNombre() %></td>
                    <td><%= producto.getDescripcion() %></td>
                    <td><%= producto.getPrecio() %></td>
                    <td><%= producto.getStock() %></td>
                    <td><%= producto.isActivo() ? "Sí" : "No" %></td>
                    <td><%= producto.getIdCategoria() %></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/editarProducto?id=<%= producto.getId() %>">
                            Editar
                        </a>
                    </td>
                </tr>
            <% } %>
        <% } %>
    </table>

</body>
</html>