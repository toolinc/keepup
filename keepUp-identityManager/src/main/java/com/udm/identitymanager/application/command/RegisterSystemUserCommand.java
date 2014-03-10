// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.application.command;

import java.util.Date;

/**
 * Command that specifies that a system user should be created.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class RegisterSystemUserCommand extends UserCommand {

    private String systemName;
    private PersonCommand administrator;

    public RegisterSystemUserCommand(String username, String password, String systemName,
                                     String firstName, String lastName, String gender,
                                     Date dateOfBirth, boolean enabled, Date startDate,
                                     Date endDate, String emailAddress, String telephone) {
        super(username, password, enabled, startDate, endDate);
        setSystemName(systemName);
        setAdministrator(new PersonCommand(firstName, lastName, gender, dateOfBirth, emailAddress,
                telephone));
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public PersonCommand getAdministrator() {
        return administrator;
    }

    public void setAdministrator(PersonCommand administrator) {
        this.administrator = administrator;
    }
}
