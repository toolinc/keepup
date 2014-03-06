// Copyright 2014 University of Detroit Mercy.

package com.udm.common.adapter.jpa.event;

import static javax.transaction.Transactional.TxType.REQUIRED;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.event.DomainEvent;
import com.udm.common.domain.repository.Repository;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Jpa implementation for the {@link com.udm.common.adapter.jpa.event.Event} repository.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class JpaEventRepository extends AssertionConcern {

    private final Repository<Event> repository;

    @Inject
    public JpaEventRepository(Repository<Event> repository) {
        assertArgumentNotNull(repository, "Repository cannot be null.");
        this.repository = repository;
    }

    /**
     * Stores a {@link com.udm.common.domain.event.DomainEvent} on the persistent storage.
     *
     * @param domainEvent the event that will be stored
     * @return the new produced {@link com.udm.common.adapter.jpa.event.Event}
     */
    @Transactional(REQUIRED)
    public Event create(DomainEvent domainEvent) {
        Event event = Event.Builder.newBuilder()
                .setMessage(domainEvent)
                .build();
        repository.create(event);
        return event;
    }
}
