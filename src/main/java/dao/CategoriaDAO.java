package dao; 
import modelo.Categoria; // 
import util.DBConnection; // Importa la clase que maneja la conexión a la BD
import java.sql.Connection; // Representa la conexión a la base de datos
import java.sql.PreparedStatement; // Sirve para preparar consultas SQL
import java.sql.ResultSet; // Guarda el resultado de una consulta SELECT
import java.sql.SQLException; // Representa errores de SQL
import java.util.ArrayList; 
import java.util.List; 


public class CategoriaDAO { 
	
	public List<Categoria> listarCategorias() { // Método que devuelve una lista de categorías
		
		ArrayList<Categoria> categorias = new ArrayList<>(); // Crea una lista vacía para guardar categorías
		String sql = "SELECT * FROM categoria"; // Consulta SQL para traer todas las categorías
		
		try { // Intenta ejecutar el código que puede dar error
            Connection con = DBConnection.getConnection(); // Obtiene la conexión a la base de datos
            PreparedStatement ps = con.prepareStatement(sql); // Prepara la consulta SQL
            ResultSet rs = ps.executeQuery(); // Ejecuta la consulta y guarda el resultado

            while (rs.next()) { // Recorre cada fila del resultado
                Categoria c = new Categoria(); // Crea un nuevo objeto Categoria
                
                c.setId(rs.getInt("id")); // Asigna al objeto el valor de la columna id
                c.setNombre(rs.getString("nombre")); // Asigna el valor de la columna nombre
                c.setDescripcion(rs.getString("descripcion")); // Asigna el valor de la columna descripcion
                
                categorias.add(c); // Agrega el objeto a la lista
            }

        } catch (SQLException e) { // Si ocurre un error SQL, entra acá
            e.printStackTrace(); // Muestra el error en consola
        }

        return categorias; // Devuelve la lista de categorías
	}
}
