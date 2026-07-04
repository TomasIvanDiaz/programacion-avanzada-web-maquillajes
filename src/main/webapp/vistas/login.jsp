<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body class="centrado">
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

        <% String registrado = request.getParameter("registrado");
           if ("true".equals(registrado)) { %>
             <p style="color: green; margin-top: 12px; font-size: 0.9rem;">¡Registro exitoso! Ya podés iniciar sesión.</p>
        <% } %>

        <p style="margin-top: 16px; font-size: 0.9rem;">¿No tenés cuenta? <a href="<%= request.getContextPath() %>/registro">Registrate</a></p>
    </div>
</body>
</html>
