package service;

import model.Beneficiario;
import persistence.dao.BeneficiarioDao;

import java.util.List;

public class BeneficiarioService {
    private BeneficiarioDao beneficiarioDao = new BeneficiarioDao();

    public void inserir(Beneficiario beneficiario) {
        beneficiarioDao.inserir(beneficiario);
    }

    public List<Beneficiario> listar() {
        return beneficiarioDao.listar();
    }

    public Beneficiario buscarPorId(int id) {
        return beneficiarioDao.buscarPorId(id);
    }

    public List<Beneficiario> buscarPorNome(String nome) {
        return beneficiarioDao.buscarPorNome(nome);
    }

    public void atualizar(Beneficiario beneficiario) {
        beneficiarioDao.atualizar(beneficiario);
    }

    public void deletar(int id) {
        beneficiarioDao.deletar(id);
    }
}
