// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.application;

import com.udm.identitymanager.PersistenceTest;
import com.udm.identitymanager.application.command.RegisterPersonUserCommand;
import com.udm.identitymanager.domain.IdentityManagementException;

import org.junit.Test;

import java.util.GregorianCalendar;

import javax.inject.Inject;

/**
 * Test for the class {@link com.udm.identitymanager.application.IdentityApplicationService}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class IdentityApplicationServiceTest extends PersistenceTest {

    @Inject
    private IdentityApplicationService identityApplicationService;

    @Test
    public void shouldRegisterPersonUser() throws IdentityManagementException {
        RegisterPersonUserCommand command = new RegisterPersonUserCommand("regPersonUser",
                "200!@asTksas0", "Kate", "Logan", "MALE",
                new GregorianCalendar(1956, 1, 26).getTime(), true, null, null,
                "kate.logan@gmail.com", "1234567890");
        identityApplicationService.registerPersonUser(command);
    }
}
