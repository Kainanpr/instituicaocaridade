package persistence.dao;

import model.Beneficiario;
import model.Permissao;
import model.Usuario;
import persistence.connection.ConexaoDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeneficiarioDao {
    private Connection conn;

    public void inserir(Beneficiario beneficiario) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "INSERT INTO beneficiario (nome, profissao, telefone, data_nascimento, " +
                    "endereco, numero, cidade, bairro, descricao, id_usuario) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, beneficiario.getNome());
            ps.setString(2, beneficiario.getProfissao());
            ps.setString(3, beneficiario.getTelefone());
            ps.setDate(4, Date.valueOf(beneficiario.getDataNascimento()));
            ps.setString(5, beneficiario.getEndereco());
            ps.setInt(6, beneficiario.getNumero());
            ps.setString(7, beneficiario.getCidade());
            ps.setString(8, beneficiario.getBairro());
            ps.setString(9, beneficiario.getDescricao());
            ps.setInt(10, beneficiario.getUsuario().getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Beneficiario beneficiario) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "UPDATE beneficiario SET nome = ?, profissao = ?, telefone = ?, " +
                    "data_nascimento = ?, endereco = ?, numero = ?, cidade = ?, bairro = ?, descricao = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, beneficiario.getNome());
            ps.setString(2, beneficiario.getProfissao());
            ps.setString(3, beneficiario.getTelefone());
            ps.setDate(4, Date.valueOf(beneficiario.getDataNascimento()));
            ps.setString(5, beneficiario.getEndereco());
            ps.setInt(6, beneficiario.getNumero());
            ps.setString(7, beneficiario.getCidade());
            ps.setString(8, beneficiario.getBairro());
            ps.setString(9, beneficiario.getDescricao());
            ps.setInt(10, beneficiario.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Beneficiario buscarPorId(int id) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM beneficiario b INNER JOIN usuario u ON b.id_usuario = u.id WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Beneficiario beneficiario = null;

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        Permissao.valueOf(rs.getString(19)));

                beneficiario = new Beneficiario(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), rs.getString(6),
                        rs.getInt(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), usuario);
            }

            ps.close();
            conn.close();

            return beneficiario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Beneficiario> buscarPorNome(String nome) {

        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM beneficiario b INNER JOIN usuario u ON b.id_usuario = u.id WHERE LOWER(nome) LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            List<Beneficiario> listBeneficiarios = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        Permissao.valueOf(rs.getString(19)));

                Beneficiario beneficiario = new Beneficiario(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), rs.getString(6),
                        rs.getInt(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), usuario);
                listBeneficiarios.add(beneficiario);
            }

            ps.close();
            conn.close();

            return listBeneficiarios;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Beneficiario> listar() {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM beneficiario b INNER JOIN usuario u ON b.id_usuario = u.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Beneficiario> listBeneficiarios = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        Permissao.valueOf(rs.getString(19)));

                Beneficiario beneficiario = new Beneficiario(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), rs.getString(6),
                        rs.getInt(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), usuario);
                listBeneficiarios.add(beneficiario);
            }

            ps.close();
            conn.close();

            return listBeneficiarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(int id) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "DELETE FROM beneficiario WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}












