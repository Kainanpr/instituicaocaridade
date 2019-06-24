package model;

import java.time.LocalDate;

public class Beneficiado {
    private int id;
    private String nome;
    private String profissao;
    private String telefone;
    private LocalDate dataNascimento;
    private String endereco;
    private int numero;
    private String bairro;
    private String cidade;
    private String descricao;
    private Usuario usuario;

    public Beneficiado() {

    }

    public Beneficiado(int id, String nome, String profissao, String telefone, LocalDate dataNascimento, String endereco, int numero, String bairro, String cidade, String descricao, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.profissao = profissao;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public Beneficiado(String nome, String profissao, String telefone, LocalDate dataNascimento, String endereco, int numero, String bairro, String cidade, String descricao, Usuario usuario) {
        this.nome = nome;
        this.profissao = profissao;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return nome;
    }
}
