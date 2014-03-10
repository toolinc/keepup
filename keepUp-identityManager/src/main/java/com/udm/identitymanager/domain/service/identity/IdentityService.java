// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.service.identity;

import static javax.transaction.Transactional.TxType.REQUIRED;

import com.udm.common.AssertionConcern;
import com.udm.identitymanager.application.command.ChangeUserPasswordCommand;
import com.udm.identitymanager.domain.IdentityManagementException;
import com.udm.identitymanager.domain.model.identity.PersonUser;
import com.udm.identitymanager.domain.model.identity.SystemUser;
import com.udm.identitymanager.domain.model.identity.User;
import com.udm.identitymanager.domain.repository.identity.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Provides different capabilities for the identity.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class IdentityService extends AssertionConcern {

    private static final String USER_NAME_EXIST =
            "com.udm.identitymanager.domain.service.identity.IdentityService.exist";
    private static final String USER_NAME_NOT_EXIST =
            "com.udm.identitymanager.domain.service.identity.IdentityService.notExist";

    private final UserRepository<PersonUser> personUserRepository;
    private final UserRepository<SystemUser> systemUserRepository;
    private final UserRepository<User> userRepository;

    @Inject
    public IdentityService(UserRepository<PersonUser> personUserRepository,
                           UserRepository<SystemUser> systemUserRepository,
                           UserRepository<User> userRepository) {
        assertArgumentNotNull(personUserRepository, "PersonUserRepository cannot be null.");
        assertArgumentNotNull(systemUserRepository, "SystemUserRepository cannot be null.");
        assertArgumentNotNull(userRepository, "UserRepository cannot be null.");
        this.personUserRepository = personUserRepository;
        this.systemUserRepository = systemUserRepository;
        this.userRepository = userRepository;
    }

    /**
     * Register a new {@link com.udm.identitymanager.domain.model.identity.PersonUser} on the
     * system.
     *
     * @param personUser the user that will be register
     * @throws IdentityManagementException if a user cannot be created
     */
    public void registerUser(PersonUser personUser) throws IdentityManagementException {
        if (personUserRepository.userWithUserName(personUser.getUserName()) == null) {
            personUserRepository.create(personUser);
        } else {
            throw IdentityManagementException.Builder.newBuilder()
                    .setMessage(USER_NAME_EXIST, personUser.getUserName())
                    .build();
        }
    }

    /**
     * Register a new {@link com.udm.identitymanager.domain.model.identity.SystemUser} on the
     * system.
     *
     * @param systemUser the user that will be register
     * @throws IdentityManagementException if a user cannot be created
     */
    public void registerUser(SystemUser systemUser) throws IdentityManagementException {
        if (systemUserRepository.userWithUserName(systemUser.getUserName()) == null) {
            systemUserRepository.create(systemUser);
        } else {
            throw IdentityManagementException.Builder.newBuilder()
                    .setMessage(USER_NAME_EXIST, systemUser.getUserName())
                    .build();
        }
    }

    /**
     * Change the existing {@link com.udm.identitymanager.domain.model.identity.User} password.
     *
     * @param command the minimum required information to change the user password
     * @throws IdentityManagementException if the password cannot be changed
     */
    @Transactional(value = REQUIRED, rollbackOn = IdentityManagementException.class)
    public void changeUserPassword(ChangeUserPasswordCommand command) throws
            IdentityManagementException {
        User user = existingUser(command.getUsername());
        if (user == null) {
            throw IdentityManagementException.Builder.newBuilder()
                    .setMessage(USER_NAME_NOT_EXIST, command.getUsername())
                    .build();
        }
        try {
            user.changePassword(command.getCurrentPassword(), command.getChangedPassword());
        } catch (NullPointerException | IllegalStateException exc) {
            throw IdentityManagementException.Builder.newBuilder().setMessage(exc.getMessage())
                    .build();
        }
    }

    private User existingUser(String username) {
        return userRepository.userWithUserName(username);
    }
}
