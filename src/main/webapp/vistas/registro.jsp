<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>
<body>

    <h1 class="pagina-titulo">Crear cuenta</h1>

    <div class="pagina-contenido">
        <div class="contenido">

            <% if (error != null) { %>
                <p class="error"><%= error %></p>
            <% } %>

            <form action="<%= request.getContextPath() %>/registro" method="post" class="formulario">

                <div>
                    <label>Nombre de usuario</label>
                    <input type="text" name="usuario" required>
                </div>

                <div>
                    <label>Contraseña</label>
                    <input type="password" name="password" required>
                </div>

                <div>
                    <label>Nombre</label>
                    <input type="text" name="nombre" required>
                </div>

                <div>
                    <label>Apellido</label>
                    <input type="text" name="apellido" required>
                </div>

                <div>
                    <label>Domicilio</label>
                    <input type="text" name="domicilio">
                </div>

                <div>
                    <label>Teléfono</label>
                    <input type="text" name="telefono">
                </div>

                <div>
                    <label>Código postal</label>
                    <input type="text" name="codigoPostal">
                </div>

                <div>
                    <label>Mail</label>
                    <input type="email" name="mail" required>
                </div>

                <div class="formulario-acciones">
                    <button type="submit" class="btn btn-primario">Registrarse</button>
                    <a href="<%= request.getContextPath() %>/login" class="btn">Volver al login</a>
                </div>

            </form>

        </div>
    </div>

</body>
</html>
