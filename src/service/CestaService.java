package service;

import model.Cesta;
import persistence.dao.CestaDao;

import java.util.List;

public class CestaService {
    private CestaDao cestaDao = new CestaDao();

    public int inserir(Cesta cesta) {
        cestaDao.inserir(cesta);

        return cestaDao.newCodCesta();
    }

    public List<Cesta> listar() {
        return cestaDao.listar();
    }

    public List<Cesta> buscarPorNomeDoBeneficiado(String nome) {
        return cestaDao.buscarPorNomeDoBeneficiado(nome);
    }
}
