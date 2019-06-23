package model;

import java.time.LocalDate;

public class Cesta {
    private int id;
    private LocalDate dataDoacao;
    private Beneficiado beneficiado;

    public Cesta() {

    }

    public Cesta(int id, LocalDate dataDoacao, Beneficiado beneficiado) {
        this.id = id;
        this.dataDoacao = dataDoacao;
        this.beneficiado = beneficiado;
    }

    public Cesta(LocalDate dataDoacao, Beneficiado beneficiado) {
        this.dataDoacao = dataDoacao;
        this.beneficiado = beneficiado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(LocalDate dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public Beneficiado getBeneficiado() {
        return beneficiado;
    }

    public void setBeneficiado(Beneficiado beneficiado) {
        this.beneficiado = beneficiado;
    }

    @Override
    public String toString() {
        return "Cesta{" +
                "id=" + id +
                ", dataDoacao=" + dataDoacao +
                ", beneficiado=" + beneficiado +
                '}';
    }
}
