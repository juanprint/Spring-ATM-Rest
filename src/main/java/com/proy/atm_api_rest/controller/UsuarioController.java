package com.proy.atm_api_rest.controller;

import com.proy.atm_api_rest.model.entity.Usuario;
import com.proy.atm_api_rest.service.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
    @Autowired
    //¿Quién implementa esta interfaz IUsuario?"
    //UsuarioImpl en service
    private IUsuario usuarioService;

    @PostMapping("usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

    @PutMapping ("usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario update(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

    @DeleteMapping("usuario/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        Usuario usuarioDelete=usuarioService.findById(id);
        usuarioService.delete(usuarioDelete);
    }

    @GetMapping("usuario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario showById(@PathVariable Integer id){
        return usuarioService.findById(id);
    }

}
