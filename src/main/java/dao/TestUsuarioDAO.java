package dao;

import modelo.Usuario;

public class TestUsuarioDAO {
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        
        Usuario u = dao.login("admin", "1234");
        
        if (u != null) {
            System.out.println("Login exitoso!");
            System.out.println("Usuario: " + u.getUsuario());
            System.out.println("Es admin: " + u.isEsAdmin());
        } else {
            System.out.println("Usuario o password incorrectos");
        }
    }
}
