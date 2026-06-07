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
				
			} catch (SQLException e) {  //Si ocurre un error de SQL, guarda en e y mostrame la traza del error
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
		public boolean eliminarProducto(int id) {
			String sql = "DELETE FROM producto WHERE id = ?";
			
			try {
				Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				ps.executeUpdate();
				return true;
			} catch (SQLException e){
				e.printStackTrace();
			    return false;
			}
				
		}
		
		public boolean modificarProducto(Producto p) {
			String sql = "UPDATE producto SET nombre=?, descripcion=?, precio=?, stock=?, activo=?, idCategoria=? WHERE id=?";
			
			try {
				Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, p.getNombre());
				ps.setString(2, p.getDescripcion());
				ps.setDouble(3, p.getPrecio());
				ps.setInt(4, p.getStock());
				ps.setBoolean(5, p.isActivo());
				ps.setInt(6, p.getIdCategoria());
				ps.setInt(7, p.getId());
				ps.executeUpdate(); //Actualizamos base de datos
				
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();
			    return false;
			}
		}
		
		public Producto buscarPorId (int id) {
			String sql = "SELECT * FROM producto WHERE id = ?";
			
			try {
				Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				
				ps.setInt(1, id);
		        ResultSet rs = ps.executeQuery();
		        
		        if (rs.next()) {
		            Producto p = new Producto();
		            p.setId(rs.getInt("id"));
		            p.setNombre(rs.getString("nombre"));
		            p.setDescripcion(rs.getString("descripcion"));
		            p.setPrecio(rs.getDouble("precio"));
		            p.setStock(rs.getInt("stock"));
		            p.setActivo(rs.getBoolean("activo"));
		            p.setIdCategoria(rs.getInt("idCategoria"));
		            return p;
		        
		        }	
			} catch (SQLException e) {
				e.printStackTrace();
			    return null;
			}
			return null;
		}
		
	}
	

