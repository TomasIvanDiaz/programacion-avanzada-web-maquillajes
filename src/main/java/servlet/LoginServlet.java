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
	
	
	//Llama al archivo JSP que muestra el formulario una vez que entra
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    request.getRequestDispatcher("/vistas/login.jsp").forward(request, response);
	}
	
	/*
	 * Hacemos logica de login:
	 * - Obtenemos usuario y contrasena ingresados
	 * - creamos un objeto de UsuarioDAO
	 * - llamamos al metodo login de UsuarioDAO y pasamos como parametros al usuario y password
	 * - Si devuelve null, redirigimo al formulario, y si devuelve un Usuario, guardamos la sesion, verificamos que no sea admin y redirigimos a otro formulario
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