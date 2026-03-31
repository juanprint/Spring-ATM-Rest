package com.proy.atm_api_rest.controller;

import com.proy.atm_api_rest.model.dto.UsuarioDto;
import com.proy.atm_api_rest.model.entity.Usuario;
import com.proy.atm_api_rest.service.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
    @Autowired
    //¿Quién implementa esta interfaz IUsuario?"
    //UsuarioImpl en service
    private IUsuario usuarioService;

    @PostMapping("usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto create(@RequestBody UsuarioDto usuarioDto){
        Usuario usuarioSave=usuarioService.save(usuarioDto);
        return UsuarioDto.builder()
                .usuarioId(usuarioSave.getUsuarioId())
                .nombre(usuarioSave.getNombre())
                .apellido(usuarioSave.getApellido())
                .dni(usuarioSave.getDni())
                .email(usuarioSave.getEmail())
                .passwordHash(usuarioSave.getPasswordHash())
                .build();
    }

    @PutMapping ("usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto update(@RequestBody UsuarioDto usuarioDto){
        Usuario usuarioUpdate=usuarioService.save(usuarioDto);
        return UsuarioDto.builder()
                .usuarioId(usuarioUpdate.getUsuarioId())
                .nombre(usuarioUpdate.getNombre())
                .apellido(usuarioUpdate.getApellido())
                .dni(usuarioUpdate.getDni())
                .email(usuarioUpdate.getEmail())
                .passwordHash(usuarioUpdate.getPasswordHash())
                .build();
    }

    @DeleteMapping("usuario/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String, Object> response=new HashMap<>();
        try {
            Usuario usuarioDelete=usuarioService.findById(id);
            usuarioService.delete(usuarioDelete);
            return new ResponseEntity<>(usuarioDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            response.put("mensaje",exDt.getMessage());
            response.put("usuario",null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("usuario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDto showById(@PathVariable Integer id){
        Usuario usuario= usuarioService.findById(id);
        return UsuarioDto.builder()
                .usuarioId(usuario.getUsuarioId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .dni(usuario.getDni())
                .email(usuario.getEmail())
                .passwordHash(usuario.getPasswordHash())
                .build();
    }

}
