package br.com.centerhelp.dominio.equipamento.repository;

import br.com.centerhelp.abstracoes.Repository;
import br.com.centerhelp.dominio.equipamento.model.TipoEquipamento;

import java.util.Collection;

public class TipoEquipamentoRepository extends Repository {

    public static Collection<TipoEquipamento> findAll() {
        String jpql = "From TipoEquipamento t";
        return manager.createQuery(jpql, TipoEquipamento.class).getResultList();
    }

    public static TipoEquipamento findById(Long id) {
        return manager.find(TipoEquipamento.class, id);
    }

    public static TipoEquipamento save(TipoEquipamento tp) {
        manager.getTransaction().begin();
        manager.persist(tp);
        manager.getTransaction().commit();
        return tp;
    }


}
