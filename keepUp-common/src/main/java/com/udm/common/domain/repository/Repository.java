// Copyright 2014 University of Detroit Mercy.

package com.udm.common.domain.repository;

import com.udm.common.domain.model.DomainObject;

import java.util.UUID;

/**
 * Specifies the contract for the Data Access Object pattern.
 *
 * @param <T> Specifies the entity of the Repository.
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public interface Repository<T extends DomainObject> {

    T create(T entity);

    T update(T entity);

    void delete(T entity);

    T findById(UUID key);
}
