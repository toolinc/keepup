// Copyright 2014 University of Detroit Mercy.

package com.udm.common.domain.repository;

import com.udm.common.domain.event.DomainEvent;
import com.udm.common.domain.event.Event;

/**
 * Specifies the contract for the Data Access Object pattern.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public interface EventRepository {

    Event create(DomainEvent domainEvent);

}
