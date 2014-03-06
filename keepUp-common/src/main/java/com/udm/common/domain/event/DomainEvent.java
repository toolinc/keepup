// Copyright 2014 University of Detroit Mercy.

package com.udm.common.domain.event;

import java.util.Date;

/**
 * Represents an event of a {@link com.udm.common.domain.model.DomainObject}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public interface DomainEvent {

    /**
     * Provides the event version.
     *
     * @return the version number
     */
    public int getEventVersion();

    /**
     * Provides the date in which the event was produced
     *
     * @return the date of the event.
     */
    public Date getOccurredOn();
}
