package persistence.dao;

import model.Cesta;
import persistence.connection.ConexaoDb;

import java.sql.*;
import java.time.ZoneId;

public class CestaDao {
    private Connection conn;

    public void inserir(Cesta cesta) {
        ZoneId zoneId = ZoneId.systemDefault();

        try {
            conn = ConexaoDb.getConnection();
            String sql = "INSERT INTO cesta (data_doacao, id_beneficiado) " +
                    "VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(cesta.getDataDoacao()));
            ps.setInt(2, cesta.getBeneficiado().getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int newCodCesta() {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT MAX(id) FROM cesta";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Integer newCodCesta = null;

            while (rs.next()) {
                newCodCesta = rs.getInt(1);
            }

            ps.close();
            conn.close();

            return newCodCesta;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
