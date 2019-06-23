package persistence.dao;

import model.Alimento;
import model.Permissao;
import model.Usuario;
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
            String sql = "INSERT INTO alimento (nome_alimento, data_validade, qtd_estoque, tipo, id_usuario) " +
                    "VALUES(?, ?, ?, ? ,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, alimento.getNomeAlimento());
            ps.setDate(2, Date.valueOf(alimento.getDataValidade()));
            ps.setInt(3, alimento.getQtdEstoque());
            ps.setString(4, alimento.getTipo());
            ps.setInt(5, alimento.getUsuario().getId());
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
            String sql = "UPDATE alimento SET nome_alimento = ?, data_validade = ?, qtd_estoque = ?, tipo = ?" +
                    " WHERE id_alimento = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, alimento.getNomeAlimento());
            ps.setDate(2, Date.valueOf(alimento.getDataValidade()));
            ps.setInt(3, alimento.getQtdEstoque());
            ps.setString(4, alimento.getTipo());
            ps.setInt(5, alimento.getId());
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
            String sql = "SELECT * FROM alimento a INNER JOIN usuario u ON a.id_usuario = u.id WHERE LOWER(tipo) LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + tipo + "%");
            ResultSet rs = ps.executeQuery();
            List<Alimento> listAlimentos = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        Permissao.valueOf(rs.getString(14)));

                Alimento alimento = new Alimento(rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getInt(4),
                        rs.getString(5),
                        usuario);
                listAlimentos.add(alimento);
            }

            ps.close();
            conn.close();

            return listAlimentos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Alimento buscarPorNome(String nome) {

        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM alimento a INNER JOIN usuario u ON a.id_usuario = u.id WHERE nome_alimento LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            List<Alimento> listAlimentos = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        Permissao.valueOf(rs.getString(14)));

                Alimento alimento = new Alimento(rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getInt(4),
                        rs.getString(5),
                        usuario);
                listAlimentos.add(alimento);
            }

            ps.close();
            conn.close();

            if (listAlimentos.size() > 0) {
                return listAlimentos.get(0);
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Alimento> listar() {

        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM alimento a INNER JOIN usuario u ON a.id_usuario = u.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Alimento> listAlimentos = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        Permissao.valueOf(rs.getString(14)));

                Alimento alimento = new Alimento(rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getInt(4),
                        rs.getString(5),
                        usuario);
                listAlimentos.add(alimento);
            }

            ps.close();
            conn.close();

            return listAlimentos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Alimento> listarVencidos() {

        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM alimento a INNER JOIN usuario u ON a.id_usuario = u.id WHERE data_validade < NOW()";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Alimento> listAlimentos = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        Permissao.valueOf(rs.getString(14)));

                Alimento alimento = new Alimento(rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getInt(4),
                        rs.getString(5),
                        usuario);
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
