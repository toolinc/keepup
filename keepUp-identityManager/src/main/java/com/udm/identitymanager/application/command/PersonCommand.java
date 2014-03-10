// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.application.command;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.udm.common.application.command.Command;

import java.util.Date;

/**
 * Command represents a person.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class PersonCommand implements Command {

    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private String emailAddress;
    private String telephone;

    public PersonCommand(String firstName, String lastName, String gender, Date dateOfBirth,
                         String emailAddress, String telephone) {
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setDateOfBirth(dateOfBirth);
        setEmailAddress(emailAddress);
        setTelephone(telephone);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        checkState(!isNullOrEmpty(gender));
        this.gender = gender.toUpperCase();
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
