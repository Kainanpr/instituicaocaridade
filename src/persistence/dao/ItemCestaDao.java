package persistence.dao;

import model.ItemCesta;
import persistence.connection.ConexaoDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemCestaDao {
    private Connection conn;

    public void inserir(ItemCesta itemCesta) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "INSERT INTO item_cesta (quantidade, id_cesta, id_alimento) " +
                    "VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, itemCesta.getQuantidade());
            ps.setInt(2, itemCesta.getCesta().getId());
            ps.setInt(3, itemCesta.getAlimento().getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
