package br.com.centerhelp.abstracoes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class Repository {

    public static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");

    public static final EntityManager manager = factory.createEntityManager();


}
