package org.hackathon.service;

import org.hackathon.dao.UsuarioDao;
import org.hackathon.model.Usuario;

import java.sql.SQLException;
import java.util.List;


public class UsuarioService {
    private UsuarioDao usuarioDao;

    public UsuarioService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public void addUsuario(Usuario usuario) throws SQLException {
        usuarioDao.addUsuario(usuario);
    }

    public void updateUsuario(Usuario usuario) throws SQLException {
        usuarioDao.updateUsuario(usuario);
    }

    public void deleteUsuario(int id) throws SQLException {
        usuarioDao.deleteUsuario(id);
    }

    public Usuario getUsuarioById(int id) throws SQLException {
        return usuarioDao.getUsuarioById(id);
    }

    public List<Usuario> getAllUsuarios() throws SQLException {
        return usuarioDao.getAllUsuarios();
    }
}
