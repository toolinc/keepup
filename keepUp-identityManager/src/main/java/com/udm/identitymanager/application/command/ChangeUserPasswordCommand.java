// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.application.command;

import com.udm.common.application.command.Command;

/**
 * Command that specifies that a change of password is required.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class ChangeUserPasswordCommand implements Command {

    private String username;
    private String currentPassword;
    private String changedPassword;

    public ChangeUserPasswordCommand(String aUsername, String aCurrentPassword,
                                     String aChangedPassword) {
        this.username = aUsername;
        this.currentPassword = aCurrentPassword;
        this.changedPassword = aChangedPassword;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrentPassword() {
        return this.currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getChangedPassword() {
        return this.changedPassword;
    }

    public void setChangedPassword(String changedPassword) {
        this.changedPassword = changedPassword;
    }
}
