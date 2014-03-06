// Copyright 2014 University of Detroit Mercy.

package com.udm.common.domain.event;

import java.util.Collection;

/**
 * Specifies the contract for a {@link com.udm.common.domain.event.DomainEvent} publisher.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public interface EventPublisher {

    /**
     * Publishes a message.
     *
     * @param domainEvent the message to be publish
     * @param <T> the type of the message
     */
    <T extends DomainEvent> void publish(T domainEvent);

    /**
     * Publishes a collection of messages.
     *
     * @param domainEvents the collection of messages to be published
     * @param <T> the type of the messages
     */
    <T extends DomainEvent> void publishAll(Collection<T> domainEvents);
}
