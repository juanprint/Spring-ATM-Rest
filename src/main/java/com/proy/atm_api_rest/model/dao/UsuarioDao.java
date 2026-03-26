package com.proy.atm_api_rest.model.dao;

import com.proy.atm_api_rest.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario,Integer> {

}
