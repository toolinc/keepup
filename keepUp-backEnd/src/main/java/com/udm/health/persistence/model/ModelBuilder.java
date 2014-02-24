// Copyright 2014 University of Detroit Mercy.

package com.udm.health.persistence.model;

import java.io.Serializable;

/**
 * Specifies the contract of the builder pattern for an entity.
 *
 * @param <T> The entity which the builder will create a new instance.
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public interface ModelBuilder<T extends Model> extends Serializable {

    /**
     * Creates a instances of a given Model.
     *
     * @return a new instance.
     */
    T build();
}
