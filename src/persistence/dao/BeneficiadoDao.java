package persistence.dao;

import model.Beneficiado;
import model.Permissao;
import model.Usuario;
import persistence.connection.ConexaoDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeneficiadoDao {
    private Connection conn;

    public void inserir(Beneficiado beneficiado) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "INSERT INTO beneficiado (nome, profissao, telefone, data_nascimento, " +
                    "endereco, numero, cidade, bairro, descricao, id_usuario) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, beneficiado.getNome());
            ps.setString(2, beneficiado.getProfissao());
            ps.setString(3, beneficiado.getTelefone());
            ps.setDate(4, Date.valueOf(beneficiado.getDataNascimento()));
            ps.setString(5, beneficiado.getEndereco());
            ps.setInt(6, beneficiado.getNumero());
            ps.setString(7, beneficiado.getCidade());
            ps.setString(8, beneficiado.getBairro());
            ps.setString(9, beneficiado.getDescricao());
            ps.setInt(10, beneficiado.getUsuario().getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Beneficiado beneficiado) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "UPDATE beneficiado SET nome = ?, profissao = ?, telefone = ?, " +
                    "data_nascimento = ?, endereco = ?, numero = ?, cidade = ?, bairro = ?, descricao = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, beneficiado.getNome());
            ps.setString(2, beneficiado.getProfissao());
            ps.setString(3, beneficiado.getTelefone());
            ps.setDate(4, Date.valueOf(beneficiado.getDataNascimento()));
            ps.setString(5, beneficiado.getEndereco());
            ps.setInt(6, beneficiado.getNumero());
            ps.setString(7, beneficiado.getCidade());
            ps.setString(8, beneficiado.getBairro());
            ps.setString(9, beneficiado.getDescricao());
            ps.setInt(10, beneficiado.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Beneficiado buscarPorId(int id) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM beneficiado b INNER JOIN usuario u ON b.id_usuario = u.id WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Beneficiado beneficiado = null;

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        Permissao.valueOf(rs.getString(19)));

                beneficiado = new Beneficiado(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), rs.getString(6),
                        rs.getInt(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), usuario);
            }

            ps.close();
            conn.close();

            return beneficiado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Beneficiado> buscarPorNome(String nome) {

        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM beneficiado b INNER JOIN usuario u ON b.id_usuario = u.id WHERE LOWER(nome) LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            List<Beneficiado> listBeneficiados = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        Permissao.valueOf(rs.getString(19)));

                Beneficiado beneficiado = new Beneficiado(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), rs.getString(6),
                        rs.getInt(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), usuario);
                listBeneficiados.add(beneficiado);
            }

            ps.close();
            conn.close();

            return listBeneficiados;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Beneficiado> listar() {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM beneficiado b INNER JOIN usuario u ON b.id_usuario = u.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Beneficiado> listBeneficiados = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        Permissao.valueOf(rs.getString(19)));

                Beneficiado beneficiado = new Beneficiado(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), rs.getString(6),
                        rs.getInt(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), usuario);
                listBeneficiados.add(beneficiado);
            }

            ps.close();
            conn.close();

            return listBeneficiados;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(int id) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "DELETE FROM beneficiado WHERE id = ?";
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












