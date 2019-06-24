package service;

import model.Cesta;
import persistence.dao.CestaDao;

public class CestaService {
    private CestaDao cestaDao = new CestaDao();

    public int inserir(Cesta cesta) {
        cestaDao.inserir(cesta);

        return cestaDao.newCodCesta();
    }
}
