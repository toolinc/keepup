// Copyright 2014 University of Detroit Mercy.

package com.udm.common.adapter.jpa.event;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.event.DomainEvent;
import com.udm.common.domain.model.DomainObjectBuilder;
import com.udm.common.domain.model.DomainObjectConcurrencySafe;
import com.udm.common.serializer.EventSerializer;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Stores a domain event.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@Table(name = "Event")
public class Event extends DomainObjectConcurrencySafe {

    @NotNull
    @Id
    @Column(name = "idEvent")
    private UUID id;

    @NotNull
    @Column(name = "type", length = 500, nullable = false)
    private String type;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "occurredOn", nullable = false)
    private Date occurredOn;

    @NotNull
    @Column(name = "eventId", nullable = false)
    private Integer eventId;

    @NotNull
    @Column(name = "message", length = 5000, nullable = false)
    private String message;

    @Deprecated
    public Event() {
    }

    private Event(Builder builder) {
        setId(builder.id);
        setType(builder.type);
        setOccurredOn(builder.occurredOn);
        setEventId(builder.eventId);
        setMessage(builder.message);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        assertArgumentNotNull(id, "Id cannot be null.");
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        assertArgumentNotNull(type, "Type cannot be null.");
        this.type = type;
    }

    public Date getOccurredOn() {
        return newDate(occurredOn);
    }

    public void setOccurredOn(Date occurredOn) {
        assertArgumentNotNull(occurredOn, "Date cannot be null.");
        this.occurredOn = newDate(occurredOn);
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        assertArgumentNotNull(eventId, "EventId cannot be null.");
        this.eventId = eventId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        assertArgumentNotNull(message, "Message cannot be null.");
        this.message = message;
    }

    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> T toDomainEvent() {
        Class<T> domainEventClass = null;
        try {
            domainEventClass = (Class<T>) Class.forName(getType());
        } catch (Exception e) {
            throw new IllegalStateException("Class load error, because: " + e.getMessage());
        }
        T domainEvent = EventSerializer.instance().deserialize(getMessage(), domainEventClass);
        return domainEvent;
    }

    /**
     * Builder of {@link Event} instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder extends AssertionConcern implements DomainObjectBuilder<Event> {

        private UUID id;
        private Date occurredOn;
        private int eventId;
        private String type;
        private String message;

        private Builder() {
        }

        public Builder setMessage(DomainEvent domainEvent) {
            assertArgumentNotNull(domainEvent, "DomainEvent cannot be null.");
            assertArgumentNotNull(domainEvent.getOccurredOn(), "OccurredOn cannot be null.");
            type = domainEvent.getClass().getName();
            message = EventSerializer.instance().serialize(domainEvent);
            occurredOn = domainEvent.getOccurredOn();
            eventId = domainEvent.getEventVersion();
            assertArgumentNotNull(message, "Message cannot be null.");
            return this;
        }

        /**
         * Creates a instances of {@link Event} given the specified
         * characteristics on the {@link Event.Builder}.
         *
         * @return a new instance {@link Event}
         */
        @Override
        public Event build() {
            id = UUID.randomUUID();
            return new Event(this);
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of {@link Event.Builder}
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
