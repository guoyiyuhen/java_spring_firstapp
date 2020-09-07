package com.miiti.firstapp.infrastructure.repository;

import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.EntityManager;
import java.sql.SQLException;

abstract class HibernateSupport<T>  {

    private EntityManager entityManager;

    HibernateSupport(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public void save(T object) {
        entityManager.persist(object);
        entityManager.flush();
    }
}

