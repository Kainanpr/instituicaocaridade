package persistence.dao;

import model.Beneficiado;
import model.Cesta;
import persistence.connection.ConexaoDb;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

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

    public List<Cesta> listar() {

        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM cesta c INNER JOIN beneficiado b ON c.id_beneficiado = b.id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Cesta> listaCestas = new ArrayList<>();

            while (rs.next()) {
                Beneficiado beneficiado = new Beneficiado(rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8).toLocalDate(),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        null);

                Cesta cesta = new Cesta(rs.getInt(1),
                        rs.getDate(2).toLocalDate(),
                        beneficiado);
                listaCestas.add(cesta);
            }

            ps.close();
            conn.close();

            return listaCestas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cesta> buscarPorNomeDoBeneficiado(String nome) {

        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM cesta c INNER JOIN beneficiado b ON c.id_beneficiado = b.id WHERE LOWER(b.nome) LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            List<Cesta> listaCestas = new ArrayList<>();

            while (rs.next()) {
                Beneficiado beneficiado = new Beneficiado(rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8).toLocalDate(),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        null);

                Cesta cesta = new Cesta(rs.getInt(1),
                        rs.getDate(2).toLocalDate(),
                        beneficiado);
                listaCestas.add(cesta);
            }

            ps.close();
            conn.close();

            return listaCestas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
