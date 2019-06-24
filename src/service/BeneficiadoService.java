package service;

import model.Beneficiado;
import persistence.dao.BeneficiadoDao;

import java.util.List;

public class BeneficiadoService {
    private BeneficiadoDao beneficiadoDao = new BeneficiadoDao();

    public void inserir(Beneficiado beneficiado) {
        beneficiadoDao.inserir(beneficiado);
    }

    public List<Beneficiado> listar() {
        return beneficiadoDao.listar();
    }

    public Beneficiado buscarPorId(int id) {
        return beneficiadoDao.buscarPorId(id);
    }

    public List<Beneficiado> buscarPorNome(String nome) {
        return beneficiadoDao.buscarPorNome(nome);
    }

    public Beneficiado buscarPorNomeCompleto(String nome) {
        return beneficiadoDao.buscarPorNomeCompleto(nome);
    }

    public void atualizar(Beneficiado beneficiado) {
        beneficiadoDao.atualizar(beneficiado);
    }

    public void deletar(int id) {
        beneficiadoDao.deletar(id);
    }
}
