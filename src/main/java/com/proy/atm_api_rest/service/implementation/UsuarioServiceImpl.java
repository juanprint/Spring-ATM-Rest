package com.proy.atm_api_rest.service.implementation;

import com.proy.atm_api_rest.model.dao.interfaces.IUsuarioDao;
import com.proy.atm_api_rest.model.dto.UsuarioDto;
import com.proy.atm_api_rest.model.entity.UsuarioEntity;
import com.proy.atm_api_rest.service.interfaces.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//service recibe un dto y retorna un entity
@Service
public class UsuarioServiceImpl implements IUsuarioService {
    //Se le pide al Dao que nos traiga informacion pero este nos la trae como
    //entity, es necesario mapearlas a dto para devolverlas al controller
    @Autowired
    private IUsuarioDao usuarioDao;

    //el findAll de dao retorna entity pero el findAll de service un dto
    @Override
    public List<UsuarioDto> findAll() {
        //Convierte un UsuarioDto a entity y viceversa
        ModelMapper modelMapper=new ModelMapper();
        return this.usuarioDao.findAll()
                .stream()                                  //entity a dto
                .map( entity -> modelMapper.map(entity,UsuarioDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDto findById(Integer id) {
        //Se ubica el usuario como entidad
        Optional<UsuarioEntity> usuarioEntity=this.usuarioDao.findById(id);
        //Si lo encontro existe
        if(usuarioEntity.isPresent()){
            ModelMapper modelMapper=new ModelMapper();
            UsuarioEntity currentUsuario=usuarioEntity.get();
            //entonces mapeamos para deolverlo como dto
            return modelMapper.map(currentUsuario,UsuarioDto.class);
        }else {
            return new UsuarioDto();
        }
    }

    @Override
    public UsuarioDto createUser(UsuarioDto usuarioDTO) {
        try {
            //recibe el dto y lo transforma  entity
            ModelMapper modelMapper=new ModelMapper();
            UsuarioEntity usuarioEntity=modelMapper.map(usuarioDTO,UsuarioEntity.class);
            //ahora se guarda en la bd
            this.usuarioDao.saveUsuario(usuarioEntity);
            //retorna dto
            return usuarioDTO;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al guardar el usuario");
        }
    }

    @Override
    public UsuarioDto updateUser(UsuarioDto usuarioDto,Integer id) {
        //Se ubica el usuario como entidad
        Optional<UsuarioEntity> usuarioEntity=this.usuarioDao.findById(id);
        //El usuario el cual quiero actualizar existe?
        if (usuarioEntity.isPresent()){
            //El usuario encontrado lo alcualizaremos con los datos del dto
            UsuarioEntity currentUsuarioEntity=usuarioEntity.get();
            currentUsuarioEntity.setNombre(usuarioDto.getNombre());
            currentUsuarioEntity.setApellido(usuarioDto.getApellido());
            currentUsuarioEntity.setDni(usuarioDto.getDni());
            currentUsuarioEntity.setEmail(usuarioDto.getEmail());
            currentUsuarioEntity.setPasswordHash(usuarioDto.getPasswordHash());
            //usuarioDao genera una sentencia sql update
            this.usuarioDao.updateUsuario(currentUsuarioEntity);
            //retorno
            ModelMapper modelMapper=new ModelMapper();
            return modelMapper.map(currentUsuarioEntity, UsuarioDto.class);
        }else{
            throw new IllegalArgumentException("El usuario no existe");
        }
    }

    @Override
    public String deleteUser(Integer id) {
        //Se ubica el usuario como entidad
        Optional<UsuarioEntity> usuarioEntity=this.usuarioDao.findById(id);
        if(usuarioEntity.isPresent()){
            //Usuario a eliminar
            UsuarioEntity currentUsuarioEntity=usuarioEntity.get();
            //Eliminamos el usuario en la bd
            this.usuarioDao.deleteUsuario(currentUsuarioEntity);
            return "Usuario con el ID "+id+" ha sido eliminado";
        }else{
            return "El usuario con ID"+id+" no existe";
        }
    }
}
