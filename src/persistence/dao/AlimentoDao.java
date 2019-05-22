package persistence.dao;

import model.Alimento;
import persistence.connection.ConexaoDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class AlimentoDao {

    private Connection conn;

    public void inserir(Alimento alimento){

        try{
            conn = ConexaoDb.getConnection();
            String sql = "INSERT INTO alimento(nome,data_validade,tipo) " +
                    " VALUES( ? , ? , ? )";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, alimento.getNome_alimento());
            ps.setDate(2, new java.sql.Date(alimento.getData_validade().getTime()));
            ps.setString(3,alimento.getTipo());
            ps.executeUpdate();
            ps.close();
            conn.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Alimento alimento){

        try{
            conn = ConexaoDb.getConnection();
            String sql = "UPDATE alimento SET nome_alimento = ?, data_validade = ?, tipo = ?" +
                    " WHERE id_alimento = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,alimento.getNome_alimento());
            ps.setDate(2,new java.sql.Date(alimento.getData_validade().getTime()));
            ps.setString(3,alimento.getTipo());
            ps.executeUpdate();
            ps.close();
            conn.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Alimento> buscar_tipo(String tipo){

        try {
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM alimento WHERE LOWER(tipo) LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + tipo + "%");
            ResultSet rs = ps.executeQuery();
            List<Alimento> listAlimento = new ArrayList<>();

            while (rs.next()) {
                Alimento alimento = new Alimento(rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4));
                listAlimento.add(alimento);
            }

            ps.close();
            conn.close();

            return listAlimento;

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Alimento> listar(){

        try{
            conn = ConexaoDb.getConnection();
            String sql = "SELECT * FROM alimento ORDER BY nome_alimento DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Alimento> listAlimentos = new ArrayList<>();

            while (rs.next()) {
                Alimento alimento = new Alimento(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4));
                listAlimentos.add(alimento);
            }

            ps.close();
            conn.close();

            return listAlimentos;

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deletar(Alimento alimento){

        try {
            conn = ConexaoDb.getConnection();
            String sql = "DELETE FROM alimento WHERE id_alimento = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,alimento.getId_alimento());
            ps.executeUpdate();
            ps.close();
            conn.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    private static Date convertToDate(LocalDate locDate) {

        return (locDate == null ? null : Date.valueOf(locDate));
    }
}
