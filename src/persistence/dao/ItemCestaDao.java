package persistence.dao;

import model.Alimento;
import model.ItemCesta;
import persistence.connection.ConexaoDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<ItemCesta> listarPorIdDaCesta(int id) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM item_cesta ic INNER JOIN alimento a ON ic.id_alimento = a.id_alimento WHERE id_cesta = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<ItemCesta> listaItemCesta = new ArrayList<>();

            while (rs.next()) {
                Alimento alimento = new Alimento(rs.getInt(5),
                        rs.getString(6),
                        rs.getDate(7).toLocalDate(),
                        rs.getInt(8),
                        rs.getString(9),
                        null);

                ItemCesta itemCesta = new ItemCesta(rs.getInt(1),
                        rs.getInt(2),
                        null,
                        alimento);
                listaItemCesta.add(itemCesta);
            }

            ps.close();
            conn.close();

            return listaItemCesta;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
