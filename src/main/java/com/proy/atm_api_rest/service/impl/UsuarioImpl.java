package com.proy.atm_api_rest.service.impl;

import com.proy.atm_api_rest.model.dao.UsuarioDao;
import com.proy.atm_api_rest.model.entity.Usuario;
import com.proy.atm_api_rest.service.IUsuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioImpl implements IUsuario {
    //Se relaciona con el dao
    @Autowired
    //UsuarioDao/UsuarioRepository
    private UsuarioDao usuarioDao;
    @Transactional
    @Override
    public Usuario save(Usuario usuario) {
    //Envía a la bd y devuelve el objeto ya guardado
        return usuarioDao.save(usuario);
    }
    @Transactional
    @Override
    //Sino se encuentra que devuelva null
    public Usuario findById(Integer id) {
        return usuarioDao.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }
}
