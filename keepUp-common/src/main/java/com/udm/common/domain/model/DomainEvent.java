// Copyright 2014 University of Detroit Mercy.

package com.udm.common.domain.model;

import java.util.Date;

/**
 * Represents an event of a {@link com.udm.common.domain.model.DomainObject}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public interface DomainEvent {

    public int eventVersion();

    public Date occurredOn();
}
