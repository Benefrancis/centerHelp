package br.com.centerhelp.dominio.cliente.repository;

import br.com.centerhelp.abstracoes.Repository;
import br.com.centerhelp.dominio.cliente.model.Cliente;
import jakarta.persistence.EntityNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ClienteRepository implements Repository<Cliente, Long> {


    static TipoClienteRepository tipoClienteRepository = new TipoClienteRepository();


    public List<Cliente> findAll() {
        String jpql = "From Cliente";
        return manager.createQuery(jpql).getResultList();
    }

    public Cliente findById(Long id) {
        return manager.find(Cliente.class, id);
    }

    @Override
    public Collection<Cliente> findByName(String text) {
        String jpql = "SELECT t FROM TipoEquipamento t WHERE LOWER(t.nome) = LOWER(:nome)";

        return manager.createQuery(jpql, Cliente.class)
                .setParameter("nome", text.toLowerCase())
                .getResultList();
    }


    public Cliente persist(Cliente c) {
        var tipo = c.getTipo();
        if (tipo != null) {
            tipo = tipoClienteRepository.findById(tipo.getId());
        }
        manager.getTransaction().begin();
        c.setTipo(tipo);
        manager.persist(c);
        manager.getTransaction().commit();
        return c;
    }

    @Override
    public Cliente update(Cliente entity) {
        Cliente existingEntity = manager.find(Cliente.class, entity.getId());

        if (existingEntity != null) {
            if (Objects.nonNull(entity.getNome())) existingEntity.setNome(entity.getNome());
            if (Objects.nonNull(entity.getTipo())) existingEntity.setTipo(entity.getTipo());
            if (Objects.nonNull(entity.getEmail())) existingEntity.setEmail(entity.getEmail());
            if (Objects.nonNull(entity.getNascimento())) existingEntity.setNascimento(entity.getNascimento());
            if (Objects.nonNull(entity.getTelefone())) existingEntity.setTelefone(entity.getTelefone());
            return existingEntity;
        } else {
            // Handle the case when the entity does not exist
            throw new EntityNotFoundException("O Cliente com ID " + entity.getId() + " não foi encontrado.");
        }
    }

    @Override
    public void delete(Cliente entity) {
        Cliente existingEntity = manager.find(Cliente.class, entity.getId());

        if (existingEntity != null) {
            manager.remove(existingEntity);
        } else {
            throw new EntityNotFoundException("O Cliente com ID " + entity.getId() + " não foi encontrado.");
        }
    }


}
