// Copyright 2014 University of Detroit Mercy.

package com.udm.common.domain.model;

import java.io.Serializable;

/**
 * Specifies the contract of the builder pattern for an entity.
 *
 * @param <T> The entity which the builder will create a new instance.
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public interface DomainObjectBuilder<T extends DomainObject> extends Serializable {

    /**
     * Creates a instances of a given DomainObject.
     *
     * @return a new instance.
     */
    T build();
}
