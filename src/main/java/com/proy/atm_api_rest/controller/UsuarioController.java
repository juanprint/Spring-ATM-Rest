package com.proy.atm_api_rest.controller;

import com.proy.atm_api_rest.model.dto.UsuarioDto;
import com.proy.atm_api_rest.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
    //El controller va a llamar al servicio,
    @Autowired
    //¿Quién implementa esta interfaz IUsuario?"
    //UsuarioImpl en service, usuarioService = UsuarioServiceImpl
    private IUsuarioService usuarioService;

    // FindAll
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDto>> findAll(){
        return new ResponseEntity<>(this.usuarioService.findAll(),HttpStatus.OK);
    }

    //Find by id
    @GetMapping("/usuario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioDto> showById(@PathVariable Integer id){
        return new ResponseEntity<>(this.usuarioService.findById(id),HttpStatus.OK);

    }

    //Create user
    //Tiene interaccion 2 veces con el dto
    @PostMapping("/crear")
    public ResponseEntity<UsuarioDto> create(@RequestBody UsuarioDto usuarioDto){
        return new ResponseEntity<>(this.usuarioService.createUser(usuarioDto),HttpStatus.CREATED);
    }

    @PutMapping ("usuario/{id}")
    public ResponseEntity<UsuarioDto> updateUser(@RequestBody UsuarioDto usuarioDto,@PathVariable Integer id){
        return new ResponseEntity<>(this.usuarioService.updateUser(usuarioDto,id),HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        return new ResponseEntity<>(this.usuarioService.deleteUser(id),HttpStatus.NO_CONTENT);
    }


}
