package service;

import model.Usuario;
import persistence.dao.UsuarioDao;

import java.util.List;

public class UsuarioService {
    private UsuarioDao usuarioDao = new UsuarioDao();

    public void inserir(Usuario usuario) {
        usuarioDao.inserir(usuario);
    }

    public List<Usuario> listar() {
        return usuarioDao.listar();
    }

    public void atualizar(Usuario usuario) {
        usuarioDao.atualizar(usuario);
    }

    public void deletar(int id) {
        usuarioDao.deletar(id);
    }
}
