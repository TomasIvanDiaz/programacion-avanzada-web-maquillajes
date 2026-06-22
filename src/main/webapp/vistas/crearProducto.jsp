<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="modelo.Producto" %>
<%@ page import="modelo.Categoria" %>
<%@ page import="java.util.List" %>
    
    <%
    //TENGO QUE TRAER PRODUCTO TMB?
    %>
    
<%
	List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Crear producto</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>
<body>

	<h1 class="pagina-titulo">Crear producto</h1>

	<div class="pagina-contenido">
		<div class="contenido">
	<form action="<%= request.getContextPath() %>/crearProducto" method="post" class="formulario">
		
		<div>
			<label>Nombre</label>
			<input type="text" name="nombre" required>
		</div>
		
		<div>
			<label>Descripcion</label>
			<textarea name="descripcion"></textarea>
		</div>
		
		<div>
        	<label>Precio</label>
            <input type="number" step="0.01" name="precio" required>
        </div>
        
        <div>
        	<label>Stock</label>
            <input type="number" name="stock" required>
        </div>
        
        <div>
        	<label>Marca</label>
            <input type="text" name="marca"> 
        </div>
		
		<div>
            <label>Imagen (nombre del archivo)</label>
            <input type="text" name="imagen">
        </div>
        
        <div>
        	<label>Categoría</label>
            <select name="idCategoria" required>
            	<% if (categorias != null) {
                   	for (Categoria categoria : categorias) { %>
                    	<option value="<%= categoria.getId() %>"><%= categoria.getNombre() %></option>
            	<% }} %>
        	</select>
        </div>

        <div>
        	<label>
            	<input type="checkbox" name="activo">
            </label>
        </div>
        
        <div class="formulario-acciones">
        	<button type="submit" class="btn btn-primario">Crear producto</button>
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