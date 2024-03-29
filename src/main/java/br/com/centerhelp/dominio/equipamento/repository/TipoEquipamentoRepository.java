package br.com.centerhelp.dominio.equipamento.repository;

import br.com.centerhelp.abstracoes.Repository;
import br.com.centerhelp.dominio.equipamento.model.TipoEquipamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;

public class TipoEquipamentoRepository implements Repository<TipoEquipamento, Long> {

    private EntityManager manager;

    public TipoEquipamentoRepository() {
        super();
        this.manager = getManager();
    }


    public Collection<TipoEquipamento> findAll() {
        String jpql = "From TipoEquipamento t";
        return manager.createQuery(jpql, TipoEquipamento.class).getResultList();
    }

    public TipoEquipamento findById(Long id) {
        return manager.find(TipoEquipamento.class, id);
    }

    @Override
    public Collection<TipoEquipamento> findByName(String text) {
        String jpql = "SELECT t FROM TipoEquipamento t WHERE  LOWER(t.nome) = LOWER(:nome)";

        return getManager()
                .createQuery(jpql, TipoEquipamento.class)
                .setParameter("nome", text)
                .getResultList();
    }

    public TipoEquipamento persist(TipoEquipamento tp) {
        try {
            manager.getTransaction().begin();
            manager.persist(tp);
            manager.getTransaction().commit();
            return tp;
        } catch (Exception e) {
            // e.printStackTrace();
            manager.getTransaction().rollback();
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TipoEquipamento update(TipoEquipamento entity) {

        TipoEquipamento existingEntity = manager.find(TipoEquipamento.class, entity.getId());

        if (existingEntity != null) {
            existingEntity.setNome(entity.getNome());
            return existingEntity;
        } else {
            throw new EntityNotFoundException("O Tipo de Equipamento com ID " + entity.getId() + " não foi encontrado.");
        }
    }

    @Override
    public void delete(TipoEquipamento entity) {

        TipoEquipamento existingEntity = manager.find(TipoEquipamento.class, entity.getId());

        if (existingEntity != null) {
            manager.remove(existingEntity);
        } else {
            // Handle the case when the entity does not exist
            throw new EntityNotFoundException("O Tipo de Equipamento com ID " + entity.getId() + " não foi encontrado.");
        }
    }


}
