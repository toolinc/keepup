// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.application.command;

import com.udm.common.application.command.Command;

import java.util.Date;

/**
 * Command that specifies that a new person user should be created.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class RegisterPersonUserCommand implements Command {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private boolean enabled;
    private Date startDate;
    private Date endDate;
    private String emailAddress;
    private String telephone;

    public RegisterPersonUserCommand(String username, String password, String firstName,
                                     String lastName, String gender, Date dateOfBirth,
                                     boolean enabled, Date startDate, Date endDate,
                                     String emailAddress, String telephone) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.enabled = enabled;
        this.startDate = startDate;
        this.endDate = endDate;
        this.emailAddress = emailAddress;
        this.telephone = telephone;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
