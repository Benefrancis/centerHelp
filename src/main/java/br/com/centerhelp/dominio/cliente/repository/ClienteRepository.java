package br.com.centerhelp.dominio.cliente.repository;

import br.com.centerhelp.abstracoes.Repository;
import br.com.centerhelp.dominio.cliente.model.Cliente;
import br.com.centerhelp.dominio.cliente.model.TipoCliente;

import java.util.List;

public abstract class ClienteRepository extends Repository {


    public static Cliente save(Cliente c) {
        var tipo = c.getTipo();
        if (tipo != null) {
            tipo = TipoClienteRepository.findById(tipo.getId());
        }
        manager.getTransaction().begin();
        c.setTipo(tipo);
        manager.persist(c);
        manager.getTransaction().commit();
        return c;
    }

    public static Cliente findById(Long id) {
        return manager.find(Cliente.class, id);
    }

    public static List<?> findAll(){
        String jpql = "From Cliente";
        return  manager.createQuery(jpql).getResultList();
    }


}
