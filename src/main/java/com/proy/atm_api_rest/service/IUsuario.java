package com.proy.atm_api_rest.service;

import com.proy.atm_api_rest.model.dto.UsuarioDto;
import com.proy.atm_api_rest.model.entity.Usuario;

public interface IUsuario {
    //save guarda y actualiza
    Usuario save(UsuarioDto usuario);
    Usuario findById(Integer id);
    void delete(Usuario usuario);
}
