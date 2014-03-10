// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.event.identity;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.event.DomainEvent;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This message represents that a password has been changed on a user.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class UserPasswordChanged extends AssertionConcern implements DomainEvent {

    private final String username;
    private final int eventVersion;
    private final Date occurredOn;

    public UserPasswordChanged(String username) {
        assertArgumentNotNull(username, "UserName cannot be null.");
        this.username = username;
        this.eventVersion = 1;
        this.occurredOn = new GregorianCalendar().getTime();
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int getEventVersion() {
        return eventVersion;
    }

    @Override
    public Date getOccurredOn() {
        return newDate(occurredOn);
    }
}
