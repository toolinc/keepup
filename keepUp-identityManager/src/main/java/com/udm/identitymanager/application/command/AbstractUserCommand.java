// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.application.command;

import com.udm.common.application.command.Command;

import java.util.Date;

/**
 * Command that specifies that a user.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public abstract class AbstractUserCommand implements Command {

    private String username;
    private String password;
    private boolean enabled;
    private Date startDate;
    private Date endDate;

    public AbstractUserCommand(String username, String password, boolean enabled, Date startDate,
                               Date endDate) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
