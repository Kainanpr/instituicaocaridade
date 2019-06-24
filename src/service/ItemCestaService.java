package service;

import model.Alimento;
import model.ItemCesta;
import persistence.dao.AlimentoDao;
import persistence.dao.ItemCestaDao;

import java.util.List;

public class ItemCestaService {
    private ItemCestaDao itemCestaDao = new ItemCestaDao();
    private AlimentoDao alimentoDao = new AlimentoDao();

    public void inserir(List<ItemCesta> listaItemCesta) {
        for (ItemCesta item : listaItemCesta) {
            itemCestaDao.inserir(item);
            atualizarEstoque(item);
        }
    }

    private void atualizarEstoque(ItemCesta itemCesta) {
        Alimento alimento = itemCesta.getAlimento();

        final int qtdEstoque = alimento.getQtdEstoque();
        final int qtdDoados = itemCesta.getQuantidade();

        alimento.setQtdEstoque(qtdEstoque - qtdDoados);

        alimentoDao.atualizar(alimento);
    }

    public List<ItemCesta> listarPorIdDaCesta(int id) {
        return itemCestaDao.listarPorIdDaCesta(id);
    }
}
