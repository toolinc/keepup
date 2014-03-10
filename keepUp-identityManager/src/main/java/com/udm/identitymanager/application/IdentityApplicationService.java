// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.application;

import com.udm.common.AssertionConcern;
import com.udm.identitymanager.application.command.ChangeUserPasswordCommand;
import com.udm.identitymanager.application.command.RegisterPersonUserCommand;
import com.udm.identitymanager.application.command.RegisterSystemUserCommand;
import com.udm.identitymanager.domain.IdentityManagementException;
import com.udm.identitymanager.domain.model.identity.*;
import com.udm.identitymanager.domain.model.identity.System;
import com.udm.identitymanager.domain.service.identity.IdentityService;

import javax.inject.Inject;

/**
 * Provides all the features related to identity.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class IdentityApplicationService extends AssertionConcern {

    private final IdentityService identityService;

    @Inject
    public IdentityApplicationService(IdentityService identityService) {
        assertArgumentNotNull(identityService, "IdentityService cannot be null.");
        this.identityService = identityService;
    }

    /**
     * Register a new {@link com.udm.identitymanager.domain.model.identity.PersonUser} on the
     * system.
     *
     * @param command the details that will be used to create the new user.
     * @return a new {@link com.udm.identitymanager.domain.model.identity.PersonUser}
     * @throws IdentityManagementException if a user cannot be created
     */
    public PersonUser registerUser(RegisterPersonUserCommand command) throws
            IdentityManagementException {
        PersonUser personUser = PersonUser.Builder.newBuilder()
                .setUserName(command.getUsername())
                .setPassword(command.getPassword())
                .setEnablement(new Enablement(
                        command.isEnabled(),
                        command.getStartDate(),
                        command.getEndDate()))
                .setPerson(Person.Builder.newBuilder()
                        .setName(command.getPerson().getFirstName())
                        .setLastName(command.getPerson().getLastName())
                        .setGender(Gender.valueOf(command.getPerson().getGender()))
                        .setDateOfBirth(command.getPerson().getDateOfBirth())
                        .setContactInformation(new ContactInformation(
                                new EmailAddress(command.getPerson().getEmailAddress()),
                                new TelephoneNumber(command.getPerson().getTelephone())
                        ))
                        .build())
                .build();
        identityService.registerUser(personUser);
        return personUser;
    }

    /**
     * Register a new {@link com.udm.identitymanager.domain.model.identity.SystemUser} on the
     * system.
     *
     * @param command the details that will be used to create the new user.
     * @return a new {@link com.udm.identitymanager.domain.model.identity.SystemUser}
     * @throws IdentityManagementException if a user cannot be created
     */
    public SystemUser registerUser(RegisterSystemUserCommand command) throws
            IdentityManagementException {
        SystemUser systemUser = SystemUser.Builder.newBuilder()
                .setUserName(command.getUsername())
                .setPassword(command.getPassword())
                .setEnablement(new Enablement(
                        command.isEnabled(),
                        command.getStartDate(),
                        command.getEndDate()))
                .setSystem(System.Builder.newBuilder()
                        .setName(command.getSystemName())
                        .setAdministrator(Person.Builder.newBuilder()
                            .setName(command.getAdministrator().getFirstName())
                            .setLastName(command.getAdministrator().getLastName())
                            .setGender(Gender.valueOf(command.getAdministrator().getGender()))
                            .setDateOfBirth(command.getAdministrator().getDateOfBirth())
                            .setContactInformation(new ContactInformation(
                                new EmailAddress(command.getAdministrator().getEmailAddress()),
                                new TelephoneNumber(command.getAdministrator().getTelephone())
                            )).build())
                        .build())
                .build();
        identityService.registerUser(systemUser);
        return systemUser;
    }

    /**
     * Change the existing {@link com.udm.identitymanager.domain.model.identity.User} password.
     *
     * @param command the minimum required information to change the user password
     * @throws IdentityManagementException if the password cannot be changed
     */
    public void changeUserPassword(ChangeUserPasswordCommand command) throws
            IdentityManagementException {
        identityService.changeUserPassword(command);
    }
}
