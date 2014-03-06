// Copyright 2014 University of Detroit Mercy.

package com.udm.common.adapter.jpa.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.event.DomainEvent;
import com.udm.common.domain.event.Event;
import com.udm.common.domain.repository.EventRepository;
import com.udm.common.domain.repository.Repository;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Jpa implementation for the {@link com.udm.common.domain.repository.EventRepository}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class EventJpaRepository extends AssertionConcern implements EventRepository {

    private final Repository<Event> repository;

    @Inject
    public EventJpaRepository(Repository<Event> repository) {
        assertArgumentNotNull(repository, "Repository cannot be null.");
        this.repository = repository;
    }

    @Transactional(REQUIRED)
    @Override
    public Event create(DomainEvent domainEvent) {
        Event event = Event.Builder.newBuilder()
                .setMessage(domainEvent)
                .build();
        repository.create(event);
        return event;
    }
}
