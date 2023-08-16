package br.com.centerhelp.dominio.servico.repository;

import br.com.centerhelp.abstracoes.Repository;
import br.com.centerhelp.dominio.equipamento.model.Equipamento;
import br.com.centerhelp.dominio.servico.model.Servico;
import br.com.centerhelp.dominio.servico.model.TipoServico;
import jakarta.persistence.EntityNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ServicoRepository implements Repository<Servico, Long> {
    static TipoServicoRepository tipoServicoRepository = new TipoServicoRepository();

    public List<Servico> findAll() {
        return manager.createQuery("From Servico").getResultList();
    }

    public Servico findById(Long id) {
        return manager.find(Servico.class, id);
    }

    @Override
    public Collection<Servico> findByName(String text) {
        String jpql = "SELECT e FROM Servico e WHERE LOWER(e.nome) = LOWER(:nome)";

        return manager.createQuery(jpql, Servico.class)
                .setParameter("nome", text.toLowerCase())
                .getResultList();
    }


    public Servico persist(Servico servico) {
        var tipo = servico.getTipo();
        if (tipo != null) {
            if (tipo.getId() != null) {
                tipo = manager.find(TipoServico.class, tipo.getId());
            } else {

                tipo = tipoServicoRepository.persist(tipo);
            }
            servico.setTipo(tipo);
        }
        manager.getTransaction().begin();
        manager.persist(servico);
        manager.getTransaction().commit();
        return servico;
    }

    @Override
    public Servico update(Servico entity) {

        Servico existingEntity = manager.find(Servico.class, entity.getId());

        if (existingEntity != null) {

            if (Objects.nonNull(entity.getNome())) existingEntity.setNome(entity.getNome());
            if (Objects.nonNull(entity.getDescricao())) existingEntity.setDescricao(entity.getDescricao());
            if (Objects.nonNull(entity.getTipo())) existingEntity.setTipo(entity.getTipo());
            if (Objects.nonNull(entity.getAbertura())) existingEntity.setAbertura(entity.getAbertura());
            if (Objects.nonNull(entity.getAutorizacao())) existingEntity.setAutorizacao(entity.getAutorizacao());
            if (Objects.nonNull(entity.getInicio())) existingEntity.setInicio(entity.getInicio());
            if (Objects.nonNull(entity.getFim())) existingEntity.setFim(entity.getFim());

            return existingEntity;
        } else {
            throw new EntityNotFoundException("O Serviço com ID " + entity.getId() + " não foi encontrado.");
        }
    }

    @Override
    public void delete(Servico entity) {
        Servico existingEntity = manager.find(Servico.class, entity.getId());

        if (existingEntity != null) {
            manager.remove(existingEntity);
        } else {
            // Handle the case when the entity does not exist
            throw new EntityNotFoundException("O Serviço com ID " + entity.getId() + " não foi encontrado.");
        }
    }


}
