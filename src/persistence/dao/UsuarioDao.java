package persistence.dao;

import model.Permissao;
import model.Usuario;
import persistence.connection.ConexaoDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private Connection conn;

    public void inserir(Usuario usuario) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "insert into usuario (nome, endereco, cpf, telefone, " +
                    "login, senha, permissao) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEndereco());
            ps.setString(3, usuario.getCpf());
            ps.setString(4, usuario.getTelefone());
            ps.setString(5, usuario.getLogin());
            ps.setString(6, usuario.getSenha());
            ps.setString(7, usuario.getPermissao().toString());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario verificarAcesso(String login, String senha) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            Usuario usuarioBuscado = null;

            while (rs.next()) {
                usuarioBuscado = new Usuario(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7),
                        Permissao.valueOf(rs.getString(8)));
            }

            ps.close();
            conn.close();

            return usuarioBuscado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Usuario usuario) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "UPDATE usuario SET nome = ?, endereco = ?, cpf = ?, " +
                    "telefone = ?, login = ?, senha = ?, permissao = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEndereco());
            ps.setString(3, usuario.getCpf());
            ps.setString(4, usuario.getTelefone());
            ps.setString(5, usuario.getLogin());
            ps.setString(6, usuario.getSenha());
            ps.setString(7, usuario.getPermissao().toString());
            ps.setInt(8, usuario.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario buscarPorId(int id) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM usuario WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Usuario usuarioBuscado = null;

            while (rs.next()) {
                usuarioBuscado = new Usuario(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7),
                        Permissao.valueOf(rs.getString(8)));
            }

            ps.close();
            conn.close();

            return usuarioBuscado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> buscarPorNome(String nome) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM usuario WHERE LOWER(nome) LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            List<Usuario> listUsuarios = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7),
                        Permissao.valueOf(rs.getString(8)));
                listUsuarios.add(usuario);
            }

            ps.close();
            conn.close();

            return listUsuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> listar() {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM usuario";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Usuario> listUsuarios = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7),
                        Permissao.valueOf(rs.getString(8)));
                listUsuarios.add(usuario);
            }

            ps.close();
            conn.close();

            return listUsuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(int id) {
        try {
            conn = ConexaoDb.getConnection();
            String sql = "DELETE FROM usuario WHERE id = ?";
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












