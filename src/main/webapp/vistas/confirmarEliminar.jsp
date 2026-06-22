<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    int id = (int) request.getAttribute("id");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Eliminar producto</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>
<body>

    <h1 class="pagina-titulo">Eliminar producto</h1>

    <div class="pagina-contenido">
        <div class="contenido">

            <p>¿Estás seguro que querés eliminar este producto?</p>

            <br>

            <form action="<%= request.getContextPath() %>/eliminarProducto" method="post" class="formulario-acciones">
                <input type="hidden" name="id" value="<%= id %>">
                <button type="submit" class="btn btn-peligro">Sí, eliminar</button>
                <a href="<%= request.getContextPath() %>/panelAdmin" class="btn">Cancelar</a>
            </form>

        </div>
    </div>

</body>
</html>
