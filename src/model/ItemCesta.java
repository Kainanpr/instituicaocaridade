package model;

public class ItemCesta {
    private int id;
    private int quantidade;
    private Cesta cesta;
    private Alimento alimento;

    public ItemCesta() {

    }

    public ItemCesta(int id, int quantidade, Cesta cesta, Alimento alimento) {
        this.id = id;
        this.quantidade = quantidade;
        this.cesta = cesta;
        this.alimento = alimento;
    }

    public ItemCesta(int quantidade, Cesta cesta, Alimento alimento) {
        this.quantidade = quantidade;
        this.cesta = cesta;
        this.alimento = alimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Cesta getCesta() {
        return cesta;
    }

    public void setCesta(Cesta cesta) {
        this.cesta = cesta;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    @Override
    public String toString() {
        return "ItemCesta{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", cesta=" + cesta +
                ", alimento=" + alimento +
                '}';
    }
}
