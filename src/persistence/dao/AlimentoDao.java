package persistence.dao;

import model.Alimento;
import persistence.connection.ConexaoDb;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDao {
    private Connection conn;

    public void inserir(Alimento alimento) {
        ZoneId zoneId = ZoneId.systemDefault();

        try {
            conn = ConexaoDb.getConnection();
            String sql = "INSERT INTO alimento (nome_alimento, data_validade, tipo) " +
                    "VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, alimento.getNomeAlimento());
            ps.setDate(2, Date.valueOf(alimento.getDataValidade()));
            ps.setString(3, alimento.getTipo());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Alimento alimento) {

        try {
            conn = ConexaoDb.getConnection();
            String sql = "UPDATE alimento SET nome_alimento = ?, data_validade = ?, tipo = ?" +
                    " WHERE id_alimento = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, alimento.getNomeAlimento());
            ps.setDate(2, Date.valueOf(alimento.getDataValidade()));
            ps.setString(3, alimento.getTipo());
            ps.setInt(4, alimento.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Alimento> buscarPorTipo(String tipo) {

        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM alimento WHERE LOWER(tipo) LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + tipo + "%");
            ResultSet rs = ps.executeQuery();
            List<Alimento> listAlimentos = new ArrayList<>();

            while (rs.next()) {
                Alimento alimento = new Alimento(rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getString(4));
                listAlimentos.add(alimento);
            }

            ps.close();
            conn.close();

            return listAlimentos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Alimento> listar() {

        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM alimento ORDER BY nome_alimento DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Alimento> listAlimentos = new ArrayList<>();

            while (rs.next()) {
                Alimento alimento = new Alimento(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getString(4));
                listAlimentos.add(alimento);
            }

            ps.close();
            conn.close();

            return listAlimentos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deletar(int id) {

        try {
            conn = ConexaoDb.getConnection();
            String sql = "DELETE FROM alimento WHERE id_alimento = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
