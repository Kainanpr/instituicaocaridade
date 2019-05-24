package model;

import java.time.LocalDate;

public class Alimento {

    private int id;
    private String nomeAlimento;
    private LocalDate dataValidade;
    private String tipo;

    public Alimento() {

    }

    public Alimento(int id, String nomeAlimento, LocalDate dataValidade, String tipo) {
        this.id = id;
        this.nomeAlimento = nomeAlimento;
        this.dataValidade = dataValidade;
        this.tipo = tipo;
    }

    public Alimento(String nomeAlimento, LocalDate dataValidade, String tipo) {
        this.nomeAlimento = nomeAlimento;
        this.dataValidade = dataValidade;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeAlimento() {
        return nomeAlimento;
    }

    public void setNomeAlimento(String nomeAlimento) {
        this.nomeAlimento = nomeAlimento;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
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
                "id=" + id +
                ", nomeAlimento='" + nomeAlimento + '\'' +
                ", dataValidade=" + dataValidade +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
