// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.event.access;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.event.DomainEvent;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This message represents a newly created group on the system.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class GroupRegistered extends AssertionConcern implements DomainEvent {

    private final String name;
    private final int eventVersion;
    private final Date occurredOn;

    public GroupRegistered(String name) {
        assertArgumentNotNull(name, "Name cannot be null.");
        this.name = name;
        this.eventVersion = 1;
        this.occurredOn = new GregorianCalendar().getTime();
    }

    public String getName() {
        return name;
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
