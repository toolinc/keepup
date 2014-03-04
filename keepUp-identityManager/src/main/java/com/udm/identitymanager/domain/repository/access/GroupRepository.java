// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.repository.access;

import com.udm.identitymanager.domain.model.access.Group;

/**
 * Defines the DAO for {@link com.udm.identitymanager.domain.model.access.Group}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public interface GroupRepository {

    /**
     * Stores a new {@link com.udm.identitymanager.domain.model.access.Group} in the persistent
     * storage.
     *
     * @param group the item to store
     */
    void create(Group group);
}
