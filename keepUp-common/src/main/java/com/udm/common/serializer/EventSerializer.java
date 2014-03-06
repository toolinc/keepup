// Copyright 2014 University of Detroit Mercy.

package com.udm.common.serializer;

import com.udm.common.domain.event.DomainEvent;

/**
 * Defines a {@link com.udm.common.domain.event.DomainEvent} serializer.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class EventSerializer extends AbstractJsonSerializer {

    private static EventSerializer eventSerializer;

    private EventSerializer() {
        this(false, false);
    }

    protected EventSerializer(boolean isCompact) {
        this(false, isCompact);
    }

    protected EventSerializer(boolean isPretty, boolean isCompact) {
        super(isPretty, isCompact);
    }

    public String serialize(DomainEvent aDomainEvent) {
        String serialization = this.gson().toJson(aDomainEvent);
        return serialization;
    }

    public <T extends DomainEvent> T deserialize(String aSerialization, final Class<T> aType) {
        T domainEvent = this.gson().fromJson(aSerialization, aType);
        return domainEvent;
    }

    public static synchronized EventSerializer instance() {
        if (EventSerializer.eventSerializer == null) {
            EventSerializer.eventSerializer = new EventSerializer();
        }
        return EventSerializer.eventSerializer;
    }
}
