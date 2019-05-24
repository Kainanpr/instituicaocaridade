package service;

import model.Alimento;
import persistence.dao.AlimentoDao;

import java.util.List;

public class AlimentoService {
    private AlimentoDao alimentoDao = new AlimentoDao();

    public void inserir(Alimento alimento) {
        alimentoDao.inserir(alimento);
    }

    public void atualizar(Alimento alimento) {
        alimentoDao.atualizar(alimento);
    }

    public List<Alimento> buscarPorTipo(String tipo) {
        return alimentoDao.buscarPorTipo(tipo);
    }

    public List<Alimento> listar() {
        return alimentoDao.listar();
    }

    public void deletar(int id) {
        alimentoDao.deletar(id);
    }
}
