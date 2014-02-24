// Copyright 2014 University of Detroit Mercy.

package com.udm.health.persistence.dao;

import com.udm.health.persistence.model.Model;

import java.util.UUID;

/**
 * Specifies the contract for the Data Access Object pattern.
 *
 * @param <T> Specifies the entity of the DAO.
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public interface DAO<T extends Model> {

    T create(T entity);

    T update(T entity);

    void delete(T entity);

    T findById(UUID key);
}
