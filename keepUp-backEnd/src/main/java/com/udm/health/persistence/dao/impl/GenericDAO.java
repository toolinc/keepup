// Copyright 2014 University of Detroit Mercy.

package com.udm.health.persistence.dao.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import com.udm.health.persistence.dao.DAO;
import com.udm.health.persistence.model.Model;

import java.lang.reflect.ParameterizedType;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * This class represents a Generic Data Access Object implementation.
 *
 * @param <T> Specifies the entity of the DAO.
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class GenericDAO<T extends Model> implements DAO<T> {

    private final EntityManager em;

    @Inject
    public GenericDAO(EntityManager entityManager) {
        this.em = checkNotNull(entityManager);
    }

    @Override
    public T create(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

    @Override
    public T findById(UUID key) {
        return em.find(getEntityClass(), key);
    }

    @SuppressWarnings("unchecked")
    public Class<T> getEntityClass() {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]);
    }
}
