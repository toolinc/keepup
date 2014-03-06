// Copyright 2014 University of Detroit Mercy.

package com.udm.common.adapter.jpa.event;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.event.DomainEvent;
import com.udm.common.domain.event.EventPublisher;
import com.udm.common.domain.event.EventSubscriber;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

/**
 * Decorator of the {@link com.udm.common.domain.event.EventPublisher}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class JpaPublisherSubscriber extends AssertionConcern implements EventPublisher,
        EventSubscriber<DomainEvent> {

    private List<EventSubscriber<DomainEvent>> subscribers;
    private JpaEventRepository repository;

    @Inject
    public JpaPublisherSubscriber(JpaEventRepository repository) {
        assertArgumentNotNull(repository, "EventRepository cannot be null.");
        this.repository = repository;
        subscribe(this);
    }

    @Override
    public <T extends DomainEvent> void publish(T domainEvent) {
        Class<?> eventType = domainEvent.getClass();
        List<EventSubscriber<DomainEvent>> allSubscribers = subscribers();
        for (EventSubscriber<DomainEvent> subscriber : allSubscribers) {
            Class<?> subscribedToType = subscribedToEventType(subscriber);
            if (Objects.equals(eventType, subscribedToType) ||
                    Objects.equals(DomainEvent.class, subscribedToType)) {
                subscriber.handle(domainEvent);
            }
        }
    }

    @Override
    public <T extends DomainEvent> void publishAll(Collection<T> domainEvents) {
        for (DomainEvent domainEvent : domainEvents) {
            publish(domainEvent);
        }
    }

    @Override
    public void handle(DomainEvent domainEvent) {
        repository.create(domainEvent);
    }

    @SuppressWarnings("unchecked")
    private <T extends EventSubscriber<? extends DomainEvent>> Class<T> subscribedToEventType(
            T domainEvent) {
        return (Class<T>) implementsEventSubscriber(domainEvent.getClass());
    }

    private Class<?> implementsEventSubscriber(Class<?> clase) {
        Type[] types = clase.getGenericInterfaces();
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType param = (ParameterizedType) type;
                if (Objects.equals(EventSubscriber.class, param.getRawType())) {
                    return (Class<?>) param.getActualTypeArguments()[0];
                }
            }
        }
        if (!Objects.equals(Object.class, clase)) {
            return implementsEventSubscriber(clase.getClass().getSuperclass());
        }
        throw new IllegalStateException(
                String.format("The provided subscriber does not implements [%s].",
                        EventSubscriber.class));
    }

    public void subscribe(EventSubscriber<DomainEvent> aSubscriber) {
        ensureSubscribersList();
        subscribers().add(aSubscriber);
    }

    private void setSubscribers(List<EventSubscriber<DomainEvent>> aSubscriberList) {
        this.subscribers = aSubscriberList;
    }

    private boolean hasSubscribers() {
        return this.subscribers() != null;
    }

    private void ensureSubscribersList() {
        if (!hasSubscribers()) {
            setSubscribers(new ArrayList<EventSubscriber<DomainEvent>>());
        }
    }

    private List<EventSubscriber<DomainEvent>> subscribers() {
        return subscribers;
    }
}
