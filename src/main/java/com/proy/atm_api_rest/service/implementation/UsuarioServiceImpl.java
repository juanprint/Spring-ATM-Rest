package com.proy.atm_api_rest.service.implementation;

import com.proy.atm_api_rest.model.dao.interfaces.IUsuarioDao;
import com.proy.atm_api_rest.model.dto.UsuarioDto;
import com.proy.atm_api_rest.model.entity.UsuarioEntity;
import com.proy.atm_api_rest.service.interfaces.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    //el findAll de dao retorna entity pero el findAll de service
    //un dto
    @Override
    public List<UsuarioDto> findAll() {
        ModelMapper modelMapper=new ModelMapper();
        return this.usuarioDao.findAll()
                .stream()
                .map( entity -> modelMapper.map(entity,UsuarioDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDto findById(Integer id) {
        return null;
    }

    @Override
    public UsuarioDto createUser(UsuarioDto usuarioDTO) {
        return null;
    }

    @Override
    public UsuarioDto updateUser(UsuarioDto usuarioDto,Integer id) {
        return null;
    }

    @Override
    public String deleteUser(Integer id) {
        return "";
    }
}
