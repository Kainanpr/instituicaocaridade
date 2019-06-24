package service;

import model.Usuario;
import persistence.dao.UsuarioDao;

import java.util.List;

public class UsuarioService {
    private UsuarioDao usuarioDao = new UsuarioDao();

    public Usuario inserir(Usuario usuario) {
        usuarioDao.inserir(usuario);
        int newCodUsuario = usuarioDao.newCodUsuario();
        return usuarioDao.buscarPorId(newCodUsuario);
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

    public List<Usuario> buscarPorNome(String nome) {
        return usuarioDao.buscarPorNome(nome);
    }

    public Usuario verificarAcesso(String login, String senha) {
        return usuarioDao.verificarAcesso(login, senha);
    }
}
