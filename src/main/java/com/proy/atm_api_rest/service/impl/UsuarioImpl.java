package com.proy.atm_api_rest.service.impl;

import com.proy.atm_api_rest.model.dao.UsuarioDao;
import com.proy.atm_api_rest.model.entity.Usuario;
import com.proy.atm_api_rest.service.IUsuario;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioImpl implements IUsuario {
    //Se relaciona con el dao
    @Autowired
    //¿Quién implementa esta interfaz UsuarioDao?
    //en este caso el bean creado en UsuarioDao automaticamente
    private UsuarioDao usuarioDao;
    
    @Transactional
    @Override
    public Usuario save(Usuario usuario) {
    //toma los valores de los atributos del objeto Java
        // y construye una sentencia SQL real
        return usuarioDao.save(usuario);
    }
    @Transactional(readOnly = true)
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
