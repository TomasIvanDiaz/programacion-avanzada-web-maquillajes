package dao;

import modelo.Producto;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
	
	public List<Producto> listarProductos() {
		ArrayList<Producto> productos = new ArrayList<>();
		String sql = "SELECT * FROM producto"; //Agarramos todos los productos con sus atributos de la base de datos, los guarda como filas
		
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Producto p = new Producto();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setPrecio(rs.getDouble("precio"));
				p.setStock(rs.getInt("stock"));
				p.setActivo(rs.getBoolean("activo"));
				p.setIdCategoria(rs.getInt("idCategoria"));
				productos.add(p);
			}
				
			} catch (SQLException e) {
				e.printStackTrace();
		    }
		return productos; //Devolvemos la lista con los productos ya cargados para poder imprimirlos
	}
	
	
	public boolean insertarProducto(Producto p) {
		String sql = "INSERT INTO producto (nombre, descripcion, precio, stock, activo, idCategoria) VALUES (?, ?, ?, ?, ?, ?)"; //No incluimos el "id" porque en la base de datos es autoIncrement
		
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getDescripcion());
			ps.setDouble(3, p.getPrecio());
			ps.setInt(4, p.getStock());
			ps.setBoolean(5, p.isActivo());
			ps.setInt(6, p.getIdCategoria());
			
			int filas = ps.executeUpdate();
			if (filas > 0) {
				return true;
			} 
			return false;
		} catch (SQLException e){
			e.printStackTrace();
		    return false;
		}
		
	}
	
	//Eliminar producto ()
	
	//modificarProducto ()
	
	//buscarPorId()

}


