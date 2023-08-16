package br.com.centerhelp.dominio.equipamento.repository;

import br.com.centerhelp.abstracoes.Repository;
import br.com.centerhelp.dominio.equipamento.model.Equipamento;
import br.com.centerhelp.dominio.equipamento.model.TipoEquipamento;
import jakarta.persistence.EntityNotFoundException;

import java.util.Collection;
import java.util.Objects;

public class EquipamentoRepository implements Repository<Equipamento, Long> {

    public Collection<Equipamento> findAll() {
        String jpql = "From  Equipamento e";
        return manager.createQuery(jpql, Equipamento.class).getResultList();
    }

    public Equipamento findById(Long id) {
        return manager.find(Equipamento.class, id);
    }

    @Override
    public Collection<Equipamento> findByName(String text) {
        String jpql = "SELECT e FROM Equipamento e WHERE LOWER(e.nome) = LOWER(:nome)";

        return manager.createQuery(jpql, Equipamento.class)
                .setParameter("nome", text.toLowerCase())
                .getResultList();
    }

    public Equipamento persist(Equipamento e) {

        var tp = e.getTipo();

        if (tp != null) {

            if (tp.getId() != null) {
                tp = manager.find(TipoEquipamento.class, tp.getId());
            } else {
                TipoEquipamentoRepository tipoEquipamentoRepository = new TipoEquipamentoRepository();
                tp = tipoEquipamentoRepository.persist(tp);
            }
            e.setTipo(tp);
        }

        manager.getTransaction().begin();
        manager.merge(e);
        manager.getTransaction().commit();
        return e;
    }

    @Override
    public Equipamento update(Equipamento entity) {

        Equipamento existingEntity = manager.find(Equipamento.class, entity.getId());

        if (existingEntity != null) {
            if (Objects.nonNull(entity.getNome())) existingEntity.setNome(entity.getNome());
            if (Objects.nonNull(entity.getTipo())) existingEntity.setTipo(entity.getTipo());
            if (Objects.nonNull(entity.getNumeroDeSerie())) existingEntity.setNumeroDeSerie(entity.getNumeroDeSerie());
            return existingEntity;
        } else {
            // Handle the case when the entity does not exist
            throw new EntityNotFoundException("O Equipamento com ID " + entity.getId() + " não foi encontrado.");
        }
    }

    @Override
    public void delete(Equipamento entity) {
        Equipamento existingEntity = manager.find(Equipamento.class, entity.getId());

        if (existingEntity != null) {
            manager.remove(existingEntity);
        } else {
            // Handle the case when the entity does not exist
            throw new EntityNotFoundException("O Equipamento com ID " + entity.getId() + " não foi encontrado.");
        }
    }


}