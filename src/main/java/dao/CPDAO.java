package dao;

import modelo.CP;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CPDAO {

    public List<CP> listarCP() { //SIRVE PARA QUE CUANDO UN USUARIO SE REGISTRE, PUEDA SELECCIONAR ENTRE LOS CODIGOS POSTALES EXISTENTES
        ArrayList<CP> lista = new ArrayList<>();
        String sql = "SELECT * FROM cp";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CP cp = new CP();
                cp.setCodigoPostal(rs.getString("codigoPostal"));
                cp.setLocalidad(rs.getString("localidad"));
                cp.setProvincia(rs.getString("provincia"));
                lista.add(cp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}