package br.com.centerhelp.dominio.cliente.repository;

import br.com.centerhelp.abstracoes.Repository;
import br.com.centerhelp.dominio.cliente.model.TipoCliente;

import java.util.List;

public abstract class TipoClienteRepository extends Repository {

    public static TipoCliente findById(Long id){
     return   manager.find(TipoCliente.class, id);
    }

    public static TipoCliente save(TipoCliente t){
        manager.getTransaction().begin();
        manager.persist(t);
        manager.getTransaction().commit();
        return t;
    }

    public static List<?> findAll(){
        String jpql = "from TipoCliente";
        return manager.createQuery(jpql).getResultList();
    }

}
