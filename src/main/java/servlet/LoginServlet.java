package servlet;

import dao.UsuarioDAO;
import modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	
	//LLAMA AL ARCHIVO JSP QUE MUESTRA EL FORMULARIO UNA VEZ QUE ENTRA
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    request.getRequestDispatcher("/vistas/login.jsp").forward(request, response);
	}
	
	/*
	 * LOGICA DE LOGIN:
	 * - OBTENEMOS USUARIO Y CONTRASENA INGRESADOS
	 * - CREAMOS UN OBJETO DE USUARIODAO
	 * - LLAMAMOS AL METODO LOGIN DE USUARIODAO Y PASAMOS COMO PARAMETROS AL USUARIO Y PASSWORD
	 * - SI DEVUELVE NULL, REDIRIGIMO AL FORMULARIO, Y SI DEVUELVE UN USUARIO, GUARDAMOS LA SESION, VERIFICAMOS QUE NO SEA ADMIN Y REDIRIGIMOS A OTRO FORMULARIO
	 */
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
			
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = dao.login(usuario, password);
		
		if (user == null) {
		    // USUARIO Y/O CONTRASENA INCORRECTA
		    request.setAttribute("error", "Usuario o contraseña incorrectos");
		    request.getRequestDispatcher("/vistas/login.jsp").forward(request, response);
		} else if (user.isEsAdmin()) {
		    // CAMINO DE ADMINISTRADOR
			HttpSession sesion = request.getSession();
			sesion.setAttribute("usuario", user);
		    response.sendRedirect("panelAdmin");
		} else {
		    // CAMINO DE USUARIO
			HttpSession sesion = request.getSession();
			sesion.setAttribute("usuario", user);
		    response.sendRedirect("catalogo");
		}
		
		
		
	}
}