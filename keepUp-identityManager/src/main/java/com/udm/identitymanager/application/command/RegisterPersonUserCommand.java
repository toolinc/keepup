// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.application.command;

import java.util.Date;

/**
 * Command that specifies that a new person user should be created.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class RegisterPersonUserCommand extends UserCommand {

    private PersonCommand person;

    public RegisterPersonUserCommand(String username, String password, String firstName,
                                     String lastName, String gender, Date dateOfBirth,
                                     boolean enabled, Date startDate, Date endDate,
                                     String emailAddress, String telephone) {
        super(username, password, enabled, startDate, endDate);
        setPerson(new PersonCommand(firstName, lastName, gender, dateOfBirth, emailAddress,
                telephone));
    }

    public PersonCommand getPerson() {
        return person;
    }

    public void setPerson(PersonCommand person) {
        this.person = person;
    }
}
