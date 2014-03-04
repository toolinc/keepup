// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.repository.identity;

import com.udm.identitymanager.domain.model.identity.User;

/**
 * Defines the DAO for {@link com.udm.identitymanager.domain.model.identity.User}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public interface UserRepository {

    /**
     * Retrieves a user for the specified user name.
     *
     * @param userName the user name that will be look up
     * @return a {@code User} if found otherwise null
     */
    User userWithUserName(String userName);
}
