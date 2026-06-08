<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="modelo.Producto" %>
<%@ page import="modelo.Usuario" %>
<%@ page import="java.util.List" %>

<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catálogo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>
<body>

    <header class="catalogo-header">
        <div>
            <h1>Catálogo</h1>
            <p>Hola, <%= usuario.getNombre() %>. Estos son nuestros productos disponibles.</p>
        </div>

        <a href="<%= request.getContextPath() %>/logout" class="btn">Cerrar sesión</a>
    </header>

    <main class="catalogo-contenedor">
        <div class="catalogo-grid">

            <% for (Producto producto : productos) { %>
                <% if (producto.isActivo()) { %>

                    <article class="producto-card">
                        <div class="producto-imagen">
                            <% if (producto.getImagen() != null && !producto.getImagen().isEmpty()) { %>
                                <img src="<%= request.getContextPath() %>/img/productos/<%= producto.getImagen() %>" alt="<%= producto.getNombre() %>">
                            <% } else { %>
                                <span>Sin imagen</span>
                            <% } %>
                        </div>

                        <div class="producto-info">
                            <p class="producto-marca"><%= producto.getMarca() %></p>
                            <h2><%= producto.getNombre() %></h2>
                            <p class="producto-descripcion"><%= producto.getDescripcion() %></p>
                            <p class="producto-precio">$<%= producto.getPrecio() %></p>
                            <p class="producto-stock">Stock disponible: <%= producto.getStock() %></p>
                        </div>
                    </article>

                <% } %>
            <% } %>

        </div>
    </main>

</body>
</html>