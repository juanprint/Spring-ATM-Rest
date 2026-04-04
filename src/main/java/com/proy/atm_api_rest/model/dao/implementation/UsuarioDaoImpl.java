package com.proy.atm_api_rest.model.dao.implementation;

import com.proy.atm_api_rest.model.dao.interfaces.IUsuarioDao;
import com.proy.atm_api_rest.model.dto.UsuarioDto;
import com.proy.atm_api_rest.model.entity.UsuarioEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDaoImpl implements IUsuarioDao {

    @Override
    public List<UsuarioEntity> findAll() {
        return List.of();
    }

    @Override
    public Optional<UsuarioEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public UsuarioEntity saveUsuario(UsuarioEntity usuarioEntity) {
        return null;
    }

    @Override
    public UsuarioEntity updateUsuario(UsuarioEntity usuarioEntity) {
        return null;
    }

    @Override
    public UsuarioEntity deleteUsuario(UsuarioEntity usuarioEntity) {
        return null;
    }
}
