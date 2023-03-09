package br.com.centerhelp.dominio.equipamento.repository;

import br.com.centerhelp.abstracoes.Repository;
import br.com.centerhelp.dominio.equipamento.model.Equipamento;
import br.com.centerhelp.dominio.equipamento.model.TipoEquipamento;

import java.util.Collection;

public class EquipamentoRepository extends Repository {

    public static Collection<Equipamento> findAll() {
        String jpql = "From  Equipamento e";
        return manager.createQuery(jpql, Equipamento.class).getResultList();
    }

    public static Equipamento findById(Long id) {
        return manager.find(Equipamento.class, id);
    }

    public static Equipamento save(Equipamento e) {

        var tp = e.getTipo();

        if (tp != null) {

            if (tp.getId() != null) {
                tp = manager.find(TipoEquipamento.class, tp.getId());
            } else {
                tp = TipoEquipamentoRepository.save(tp);
            }
            e.setTipo(tp);
        }

        manager.getTransaction().begin();
        manager.merge(e);
        manager.getTransaction().commit();
        return e;
    }


}