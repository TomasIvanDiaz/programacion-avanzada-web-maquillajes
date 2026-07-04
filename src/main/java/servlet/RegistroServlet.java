package servlet;

import java.io.IOException;

import dao.UsuarioDAO;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import util.MailUtil;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // MOSTRAMOS EL FORMULARIO DE REGISTRO
        request.getRequestDispatcher("/vistas/registro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String domicilio = request.getParameter("domicilio");
        String telefono = request.getParameter("telefono");
        String codigoPostal = request.getParameter("codigoPostal");
        String mail = request.getParameter("mail");

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // VERIFICAMOS QUE EL USUARIO NO EXISTA YA EN LA BASE DE DATOS
        if (usuarioDAO.existeUsuario(usuario)) {
            request.setAttribute("error", "El nombre de usuario ya está en uso.");
            request.getRequestDispatcher("/vistas/registro.jsp").forward(request, response);
            return;
        }

        // ARMAMOS EL OBJETO USUARIO CON LOS DATOS DEL FORMULARIO
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsuario(usuario);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setDomicilio(domicilio);
        nuevoUsuario.setTelefono(telefono);
        nuevoUsuario.setCodigoPostal(codigoPostal);
        nuevoUsuario.setMail(mail);

        // REGISTRAMOS EL USUARIO EN LA BASE DE DATOS
        boolean exito = usuarioDAO.registroUsuario(nuevoUsuario);

        if (exito) {
            // ENVIAMOS EL MAIL DE BIENVENIDA AL USUARIO
            try {
                MailUtil.enviarMail(mail, nombre);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            // REDIRIGIMOS AL LOGIN CON UN MENSAJE DE EXITO
            response.sendRedirect("login?registrado=true");
        } else {
            request.setAttribute("error", "Ocurrió un error al registrarse. Intentá de nuevo.");
            request.getRequestDispatcher("/vistas/registro.jsp").forward(request, response);
        }
    }
}
