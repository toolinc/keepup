// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.service.identity;

import com.udm.common.AssertionConcern;
import com.udm.identitymanager.domain.IdentityManagementException;
import com.udm.identitymanager.domain.model.identity.PersonUser;
import com.udm.identitymanager.domain.repository.identity.UserRepository;

import javax.inject.Inject;

/**
 * Provides different capabilities for the identity.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class IdentityService extends AssertionConcern {

    private static final String USER_NAME_EXIST =
            "com.udm.identitymanager.domain.service.identity.IdentityService";
    private final UserRepository<PersonUser> personUserRepository;

    @Inject
    public IdentityService(UserRepository<PersonUser> personUserRepository) {
        assertArgumentNotNull(personUserRepository, "PersonUserRepository cannot be null.");
        this.personUserRepository = personUserRepository;
    }

    /**
     * Register a new {@link com.udm.identitymanager.domain.model.identity.PersonUser} on the
     * system.
     *
     * @param personUser the user that will be register
     * @throws IdentityManagementException if a user cannot be created
     */
    public void registerPersonUser(PersonUser personUser) throws IdentityManagementException {
        if (personUserRepository.userWithUserName(personUser.getUserName()) == null) {
            personUserRepository.create(personUser);
        } else {
            throw IdentityManagementException.Builder.newBuilder()
                    .setMessage(USER_NAME_EXIST, personUser.getUserName())
                    .build();
        }
    }
}
