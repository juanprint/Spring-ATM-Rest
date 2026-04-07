package com.proy.atm_api_rest.model.dao.implementation;

import com.proy.atm_api_rest.model.dao.interfaces.IUsuarioDao;
import com.proy.atm_api_rest.model.dto.UsuarioDto;
import com.proy.atm_api_rest.model.entity.UsuarioEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDaoImpl implements IUsuarioDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioEntity> findAll() {
        return this.em.createQuery("SELECT u FROM UsuarioEntity u").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioEntity> findById(Integer id) {
        return Optional.ofNullable(this.em.find(UsuarioEntity.class,id));
    }

    @Override
    @Transactional
    public void saveUsuario(UsuarioEntity usuarioEntity) {
        this.em.persist(usuarioEntity);
        this.em.flush();
    }

    @Override
    @Transactional
    public void updateUsuario(UsuarioEntity usuarioEntity) {
        this.em.merge(usuarioEntity);
    }

    @Override
    @Transactional
    public void deleteUsuario(UsuarioEntity usuarioEntity) {
        this.em.remove(usuarioEntity);
    }
}
