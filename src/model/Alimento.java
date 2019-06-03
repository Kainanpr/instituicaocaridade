package model;

import java.time.LocalDate;

public class Alimento {

    private int id;
    private String nomeAlimento;
    private LocalDate dataValidade;
    private int qtdEstoque;
    private String tipo;
    private Usuario usuario;

    public Alimento() {

    }

    public Alimento(int id, String nomeAlimento, LocalDate dataValidade, int qtdEstoque, String tipo, Usuario usuario) {
        this.id = id;
        this.nomeAlimento = nomeAlimento;
        this.dataValidade = dataValidade;
        this.qtdEstoque = qtdEstoque;
        this.tipo = tipo;
        this.usuario = usuario;
    }

    public Alimento(String nomeAlimento, LocalDate dataValidade, int qtdEstoque, String tipo, Usuario usuario) {
        this.nomeAlimento = nomeAlimento;
        this.dataValidade = dataValidade;
        this.qtdEstoque = qtdEstoque;
        this.tipo = tipo;
        this.usuario = usuario;
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

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "id=" + id +
                ", nomeAlimento='" + nomeAlimento + '\'' +
                ", dataValidade=" + dataValidade +
                ", qtdEstoque=" + qtdEstoque +
                ", tipo='" + tipo + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
