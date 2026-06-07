package dao;

import modelo.Usuario;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
	
	static {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Usuario login(String usuario, String password) {
		String sql = "SELECT * FROM usuario WHERE usuario = ? AND password = ?";
		
		try {
			Connection con = DBConnection.getConnection(); //ESTABLECEMOS CONEXION CON LA BASE DE DATOS
			PreparedStatement ps = con.prepareStatement(sql); //PREPARA LA BASE DE DATOS PARA PODER ENVIAR LOS VALROES REALES (Y REALIZAR LA CONSULTA)
			ps.setString(1, usuario); //COMPLETA EN LA BASE DE DATOS A LOS SIGNOS DE '?'
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery(); //DEVUELVE UNA TABLA CON LAS FILAS ENCONTRADAS (EL RS)
			
			if (rs.next()) { //EXISTE EL USUARIO
	            Usuario u = new Usuario(); // CREA UN USUARIO
	            u.setId(rs.getInt("id")); //HACE UN SET DEL ID PARA EL SERVLET, OBTENIENDOLO A PARTIR DE LA FILA RS QUE OBTIENE UN ENTERO DE LA COLUMNA 'ID'
	            u.setUsuario(rs.getString("usuario"));
	            u.setPassword(rs.getString("password"));
	            u.setEsAdmin(rs.getBoolean("esAdmin"));
	            u.setNombre(rs.getString("nombre"));
	            u.setApellido(rs.getString("apellido"));
	            u.setDomicilio(rs.getString("domicilio"));
	            u.setTelefono(rs.getString("telefono"));
	            u.setCodigoPostal(rs.getString("codigoPostal"));
	            return u; //DEVUELVE EL OBJETO
	        } else {
	            System.out.println("Usuario no encontrado");
	        }
	        
	   	} catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	    
	    return null;
	        
		}

	public Usuario buscarPorId(int id) {
	    String sql = "SELECT * FROM usuario WHERE id = ?";
	    
	    try {
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            Usuario u = new Usuario();
	            u.setId(rs.getInt("id"));
	            u.setUsuario(rs.getString("usuario"));
	            u.setPassword(rs.getString("password"));
	            u.setEsAdmin(rs.getBoolean("esAdmin"));
	            u.setNombre(rs.getString("nombre"));
	            u.setApellido(rs.getString("apellido"));
	            u.setDomicilio(rs.getString("domicilio"));
	            u.setTelefono(rs.getString("telefono"));
	            u.setCodigoPostal(rs.getString("codigoPostal"));
	            return u;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
}

