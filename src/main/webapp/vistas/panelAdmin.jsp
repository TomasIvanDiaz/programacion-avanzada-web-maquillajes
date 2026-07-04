<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="modelo.Producto" %>
<%@ page import="modelo.Categoria" %>
<%@ page import="java.util.List" %>

<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel administrador</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>
<body>

    <h1 class="pagina-titulo">Panel administrador</h1>

    <div class="pagina-contenido">
        <div class="contenido">
            <div class="contenido-header">
                <h2>Productos</h2>
                <!--ESTE BOTON DE CREAR PRODUCTO MANDA A OTRA URL DONDE APLICAMOS ESA FUNCION  -->
                <a href="<%= request.getContextPath() %>/crearProducto" class="btn btn-primario">+ Crear producto</a>
            </div>

            <table>
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

                <!-- ITERAMOS POR LA LISTA QUE OBTUVIMOS EN EL SERVLET E IMPRIMIMOS EN PANTALLA -->
                <% if (productos != null) {
                    for (Producto producto : productos) { %>
                        <tr>
                            <td><%= producto.getId() %></td>
                            <td><%= producto.getNombre() %></td>
                            <td><%= producto.getDescripcion() %></td>
                            <td><%= producto.getPrecio() %></td>
                            <td><%= producto.getStock() %></td>
                            <td><%= producto.isActivo() ? "Sí" : "No" %></td>
                            <td>
                                <% if (categorias != null) {
                                    for (Categoria c : categorias) {
                                        if (c.getId() == producto.getIdCategoria()) { %>
                                            <%= c.getNombre() %>
                                        <% }
                                    }
                                } %>
                            </td>
                            <td style="white-space: nowrap;">
                                <a href="<%= request.getContextPath() %>/editarProducto?id=<%= producto.getId() %>" class="btn btn-sm">Editar</a>
                                <button onclick="abrirModal(<%= producto.getId() %>)" class="btn btn-sm btn-peligro">Eliminar</button>
                            </td>
                        </tr>
                <% }} %>
            </table>
        </div>
    </div>

    <div class="pagina-footer">
        <a href="<%= request.getContextPath() %>/logout" class="btn">Cerrar sesión</a>
    </div>

    <!-- MODAL DE CONFIRMACION -->
    <div id="fondo-modal" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0,0,0,0.4);">
        <div style="background:white; width:340px; margin:200px auto; padding:32px; border-radius:4px; text-align:center;">
            <p style="margin-bottom:24px; font-size:1rem;">¿Estás seguro que querés eliminar este producto?</p>
            <form id="form-eliminar" action="<%= request.getContextPath() %>/eliminarProducto" method="post">
                <input type="hidden" id="input-id" name="id" value="">
                <div style="display:flex; justify-content:center; gap:12px;">
                    <button type="submit" class="btn btn-peligro">Sí, eliminar</button>
                    <button type="button" onclick="cerrarModal()" class="btn">Cancelar</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        function abrirModal(id) {
            document.getElementById("fondo-modal").style.display = "block";
            document.getElementById("input-id").value = id;
        }
        function cerrarModal() {
            document.getElementById("fondo-modal").style.display = "none";
        }
    </script>

</body>
</html>
