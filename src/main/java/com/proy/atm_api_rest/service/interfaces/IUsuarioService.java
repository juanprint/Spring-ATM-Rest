package com.proy.atm_api_rest.service.interfaces;

import com.proy.atm_api_rest.model.dto.UsuarioDto;

import java.util.List;

public interface IUsuarioService {
    //save guarda y actualiza
    List<UsuarioDto> findAll();
    UsuarioDto findById(Integer id);
    UsuarioDto createUser(UsuarioDto usuarioDTO);
    UsuarioDto updateUser(UsuarioDto usuarioDto,Integer id);
    String deleteUser(Integer id);
}
