<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <div class="caja">
        <h2>Iniciar Sesión</h2>
        <form action="login" method="post">
            <input type="text" name="usuario" placeholder="Usuario"><br><br>
            <input type="password" name="password" placeholder="Contraseña"><br><br>
            <button type="submit">Ingresar</button>
        </form>
        <% String error = (String) request.getAttribute("error");
           if (error != null) { %>
             <p class="error"><%= error %></p>
        <% } %>
    </div>
</body>
</html>
