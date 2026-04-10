package dao;

import modelo.Usuario;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
	
	public Usuario login(String usuario, String password) {
		String sql = "SELECT * FROM usuario WHERE usuario = ? AND password = ?";
		System.out.println("1er consulta sql: " + sql);
		
		try {
			Connection con = DBConnection.getConnection(); //Establecemos conexion con la base de datos
			PreparedStatement ps = con.prepareStatement(sql); //Prepara la base de datos para poder enviar los valroes reales (y realizar la consulta)
			ps.setString(1, usuario); //Completa en la base de datos a los signos de '?'
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery(); //Devuelve una tabla con las filas encontradas (el rs)
			
			if (rs.next()) { //Existe el usuario
	            Usuario u = new Usuario(); // Crea un usuario
	            u.setId(rs.getInt("id")); //Hace un set del id para el Servlet, obteniendolo a partir de la fila rs que obtiene un entero de la columna 'id'
	            u.setUsuario(rs.getString("usuario"));
	            u.setPassword(rs.getString("password"));
	            u.setEsAdmin(rs.getBoolean("esAdmin"));
	            return u; //Devuelve el objeto
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
	            return u;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
}

