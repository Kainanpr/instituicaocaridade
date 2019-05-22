package model;

import java.util.Date;

public class Alimento {

    private int id_alimento;
    private String nome_alimento;
    private Date data_validade;
    private String tipo;

    public Alimento(int id_alimento, String nome_alimento, Date data_validade, String tipo){
        this.id_alimento = id_alimento;
        this.nome_alimento = nome_alimento;
        this.data_validade = data_validade;
        this.tipo = tipo;
    }

    public int getId_alimento() {
        return id_alimento;
    }

    public void setId_alimento(int id_alimento) {
        this.id_alimento = id_alimento;
    }

    public String getNome_alimento() {
        return nome_alimento;
    }

    public void setNome_alimento(String nome_alimento) {
        this.nome_alimento = nome_alimento;
    }

    public Date getData_validade() {
        return data_validade;
    }

    public void setData_validade(Date data_validade) {
        this.data_validade = data_validade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "id_alimento=" + id_alimento +
                ", nome_alimento='" + nome_alimento + '\'' +
                ", data_validade='" + data_validade + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
