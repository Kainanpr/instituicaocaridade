package service;

import model.ItemCesta;
import persistence.dao.ItemCestaDao;

import java.util.List;

public class ItemCestaService {
    private ItemCestaDao itemCestaDao = new ItemCestaDao();

    public void inserir(List<ItemCesta> listaItemCesta) {
        for (ItemCesta item : listaItemCesta) {
            itemCestaDao.inserir(item);
        }
    }

    public List<ItemCesta> listarPorIdDaCesta(int id) {
        return itemCestaDao.listarPorIdDaCesta(id);
    }
}
