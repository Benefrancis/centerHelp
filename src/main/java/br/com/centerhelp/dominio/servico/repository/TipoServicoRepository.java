package br.com.centerhelp.dominio.servico.repository;

import br.com.centerhelp.abstracoes.Repository;
import br.com.centerhelp.dominio.servico.model.TipoServico;

import javax.swing.*;
import java.util.List;

public abstract class TipoServicoRepository extends Repository {
    public static TipoServico save(TipoServico tipo) {
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
    public static TipoServico findById(Long id) { return manager.find(TipoServico.class, id);     }
    public static List<?> findAll() { return manager.createQuery("From TipoServico").getResultList();     }
}
