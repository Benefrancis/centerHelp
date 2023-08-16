package br.com.centerhelp.dominio.servico.repository;

import br.com.centerhelp.abstracoes.Repository;
import br.com.centerhelp.dominio.servico.model.Servico;
import br.com.centerhelp.dominio.servico.model.TipoServico;

import java.util.List;

public abstract class ServicoRepository extends Repository {

    public static Servico save(Servico servico) {
        var tipo = servico.getTipo();
        if (tipo != null) {
            if (tipo.getId() != null) {
                tipo = manager.find(TipoServico.class, tipo.getId());
            } else {
                tipo = TipoServicoRepository.save(tipo);
            }
            servico.setTipo(tipo);
        }
        manager.getTransaction().begin();
        manager.persist(servico);
        manager.getTransaction().commit();
        return servico;
    }

    public static Servico findById(Long id){ return manager.find(Servico.class, id);   }

    public static List<?> findAll(){   return manager.createQuery("From Servico").getResultList(); }

}
