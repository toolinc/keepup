// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.application;

import com.udm.identitymanager.PersistenceTest;
import com.udm.identitymanager.application.command.RegisterPersonUserCommand;
import com.udm.identitymanager.application.command.RegisterSystemUserCommand;
import com.udm.identitymanager.domain.IdentityManagementException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.GregorianCalendar;

import javax.inject.Inject;

/**
 * Test for the class {@link com.udm.identitymanager.application.IdentityApplicationService}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IdentityApplicationServiceTest extends PersistenceTest {

    @Inject
    private IdentityApplicationService identityApplicationService;

    @Test
    public void shouldRegisterPersonUser() throws IdentityManagementException {
        RegisterPersonUserCommand command =
                new RegisterPersonUserCommand("IdentityAppServicePersonUser", "1956$@asTksas0",
                        "Kate", "Logan", "MALE", new GregorianCalendar(1956, 1, 26).getTime(),
                        true, null, null, "kate.logan@gmail.com", "1234567890");
        identityApplicationService.registerUser(command);
    }

    @Test
    public void shouldRegisterSystemUser() throws IdentityManagementException {
        RegisterSystemUserCommand command =
                new RegisterSystemUserCommand("IdentityAppServiceSystemUser", "1990!@2^sT85sas0",
                        "testSystem", "Kerry", "Cathcart", "MALE",
                        new GregorianCalendar(1990, 9, 9).getTime(), true, null, null,
                        "kerry.cathcart@gmail.com", "1234567890");
        identityApplicationService.registerUser(command);
    }
}
