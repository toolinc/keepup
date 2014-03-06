// Copyright 2014 University of Detroit Mercy.

package com.udm.common.domain.event;

/**
 * Specifies the contract for a {@link com.udm.common.domain.event.DomainEvent} subscriber.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 * @param <T> specifies the type of events that the subscriber handles
 */
public interface EventSubscriber<T extends DomainEvent> {

    /**
     * This call back will be invoke upon reception of a given
     * {@link com.udm.common.domain.event.DomainEvent} message.
     *
     * @param domainEvent the type of message that will be handle by the subscriber
     */
    void handle(final T domainEvent);
}
