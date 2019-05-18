package service;

import model.Usuario;
import persistence.dao.UsuarioDao;

public class UsuarioService {
    private UsuarioDao usuarioDao = new UsuarioDao();

    public void inserir(Usuario usuario) {
        usuarioDao.inserir(usuario);
    }
}
