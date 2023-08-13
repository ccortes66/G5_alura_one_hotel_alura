package com.alura.hotelalura.config;

import com.google.inject.Provider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider implements Provider<EntityManager>
{
    private final EntityManagerFactory factory;

    public EntityManagerProvider()
    {
        factory = Persistence.createEntityManagerFactory("hotel_alura");
    }

    @Override
    public EntityManager get() {
        return factory.createEntityManager();
    }


}