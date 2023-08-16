package br.com.centerhelp.abstracoes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Collection;
import java.util.Objects;

public interface Repository<T, U> {

   final static EntityManagerFactory factory = Persistence.createEntityManagerFactory("maria-db");

    default EntityManager getManager() {
        return factory.createEntityManager();
    }

    Collection<T> findAll();

    T findById(U id);

    Collection<T> findByName(String text);

    T persist(T entity);

    T update(T entity);

    void delete(T entity);

}
