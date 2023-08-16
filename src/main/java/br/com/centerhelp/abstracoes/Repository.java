package br.com.centerhelp.abstracoes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Collection;

public interface Repository<T, U> {

    public static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("maria-db");

    public static final EntityManager manager = factory.createEntityManager();

    Collection<T> findAll();

    T findById(U id);

    Collection<T> findByName(String text);

    T persist(T entity);

    T update(T entity);

    void delete(T entity);

}
