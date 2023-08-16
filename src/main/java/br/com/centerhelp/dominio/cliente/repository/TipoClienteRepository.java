package br.com.centerhelp.dominio.cliente.repository;

import br.com.centerhelp.abstracoes.Repository;
import br.com.centerhelp.dominio.cliente.model.Cliente;
import br.com.centerhelp.dominio.cliente.model.TipoCliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class TipoClienteRepository implements Repository<TipoCliente, Long> {

    private EntityManager manager;

    public TipoClienteRepository() {
        super();
        this.manager = getManager();
    }
    
    
    public List<TipoCliente> findAll() {
        String jpql = "from TipoCliente";
        return manager.createQuery(jpql).getResultList();
    }


    public TipoCliente findById(Long id) {
        return manager.find(TipoCliente.class, id);
    }

    @Override
    public Collection<TipoCliente> findByName(String text) {
        String jpql = "SELECT t FROM TipoCliente t WHERE LOWER(t.nome) = LOWER(:nome)";

        return manager.createQuery(jpql, TipoCliente.class)
                .setParameter("nome", text.toLowerCase())
                .getResultList();
    }

    public TipoCliente persist(TipoCliente t) {
        try {
            manager.getTransaction().begin();
            manager.persist(t);
            manager.getTransaction().commit();
            return t;
        } catch (Exception e) {
            // e.printStackTrace();
            manager.getTransaction().rollback();
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TipoCliente update(TipoCliente entity) {
        TipoCliente existingEntity = manager.find(TipoCliente.class, entity.getId());
        if (existingEntity != null) {
            if (Objects.nonNull(entity.getNome())) existingEntity.setNome(entity.getNome().toUpperCase());
            return existingEntity;
        } else {
            throw new EntityNotFoundException("O Tipo de Cliente com ID " + entity.getId() + " não foi encontrado.");
        }
    }

    @Override
    public void delete(TipoCliente entity) {
        TipoCliente existingEntity = manager.find(TipoCliente.class, entity.getId());
        if (existingEntity != null) {
            manager.remove(existingEntity);
        } else {
            throw new EntityNotFoundException("O Tipo de Cliente com ID " + entity.getId() + " não foi encontrado.");
        }
    }


}
