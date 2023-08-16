package br.com.centerhelp.dominio.servico.repository;

import br.com.centerhelp.abstracoes.Repository;
import br.com.centerhelp.dominio.equipamento.model.Equipamento;
import br.com.centerhelp.dominio.equipamento.model.TipoEquipamento;
import br.com.centerhelp.dominio.servico.model.TipoServico;
import jakarta.persistence.EntityNotFoundException;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

public class TipoServicoRepository implements Repository<TipoServico, Long> {

    public List<TipoServico> findAll() {
        return manager.createQuery("From TipoServico").getResultList();
    }

    public TipoServico findById(Long id) {
        return manager.find(TipoServico.class, id);
    }

    @Override
    public Collection<TipoServico> findByName(String text) {
        String jpql = "SELECT e FROM TipoServico e WHERE LOWER(e.nome) = LOWER(:nome)";

        return manager.createQuery(jpql, TipoServico.class)
                .setParameter("nome", text.toLowerCase())
                .getResultList();
    }

    public TipoServico persist(TipoServico tipo) {
        manager.getTransaction().begin();
        try {
            manager.persist(tipo);
            manager.getTransaction().commit();
            return tipo;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Já existe o tipo de serviço (" + tipo.getNome() + ") salvo no banco de dados");
            return null;
        }
    }

    @Override
    public TipoServico update(TipoServico entity) {
        TipoServico existingEntity = manager.find(TipoServico.class, entity.getId());

        if (existingEntity != null) {
            existingEntity.setNome(entity.getNome());
            return existingEntity;
        } else {
            throw new EntityNotFoundException("O Tipo de Serviço com ID " + entity.getId() + " não foi encontrado.");
        }
    }

    @Override
    public void delete(TipoServico entity) {
        TipoServico existingEntity = manager.find(TipoServico.class, entity.getId());

        if (existingEntity != null) {
            manager.remove(existingEntity);
        } else {
            // Handle the case when the entity does not exist
            throw new EntityNotFoundException("O Tipo de Serviço com ID " + entity.getId() + " não foi encontrado.");
        }
    }


}
