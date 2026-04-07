package com.proy.atm_api_rest.model.dao.interfaces;

import com.proy.atm_api_rest.model.dto.UsuarioDto;
import com.proy.atm_api_rest.model.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//ser el objeto que se encarga de acceder a los datos.
//El bean en UsuarioDao se crea automaticamente
public interface IUsuarioDao {
//Tipo Entity ya que la bd solo entiende Entitys
    List<UsuarioEntity> findAll();
    Optional<UsuarioEntity> findById(Integer id);
    void saveUsuario(UsuarioEntity usuarioEntity);
    void updateUsuario(UsuarioEntity usuarioEntity);
    void deleteUsuario(UsuarioEntity usuarioEntity);


}
