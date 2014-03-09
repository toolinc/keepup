// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.application;

import com.udm.common.AssertionConcern;
import com.udm.identitymanager.application.command.RegisterPersonUserCommand;
import com.udm.identitymanager.domain.IdentityManagementException;
import com.udm.identitymanager.domain.model.identity.ContactInformation;
import com.udm.identitymanager.domain.model.identity.EmailAddress;
import com.udm.identitymanager.domain.model.identity.Enablement;
import com.udm.identitymanager.domain.model.identity.Gender;
import com.udm.identitymanager.domain.model.identity.Person;
import com.udm.identitymanager.domain.model.identity.PersonUser;
import com.udm.identitymanager.domain.model.identity.TelephoneNumber;
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
    public PersonUser registerPersonUser(RegisterPersonUserCommand command) throws
            IdentityManagementException {
        PersonUser personUser = PersonUser.Builder.newBuilder()
                .setUserName(command.getUsername())
                .setPassword(command.getPassword())
                .setEnablement(new Enablement(
                        command.isEnabled(),
                        command.getStartDate(),
                        command.getEndDate()))
                .setPerson(Person.Builder.newBuilder()
                        .setName(command.getFirstName())
                        .setLastName(command.getLastName())
                        .setGender(Gender.valueOf(command.getGender().toUpperCase()))
                        .setDateOfBirth(command.getDateOfBirth())
                        .setContactInformation(new ContactInformation(
                                new EmailAddress(command.getEmailAddress()),
                                new TelephoneNumber(command.getTelephone())
                        ))
                        .build())
                .build();
        identityService.registerPersonUser(personUser);
        return personUser;
    }
}
