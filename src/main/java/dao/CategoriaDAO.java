package dao;

import modelo.Categoria;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
	
	public List<Categoria> listarCategorias() {
		
		ArrayList<Categoria> categorias = new ArrayList<>();
		String sql = "SELECT * FROM categoria";
		
		try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                categorias.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
	}
}
