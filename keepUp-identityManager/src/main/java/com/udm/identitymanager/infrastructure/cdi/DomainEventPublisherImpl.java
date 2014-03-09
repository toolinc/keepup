// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.infrastructure.cdi;

import com.udm.common.adapter.jpa.event.JpaPublisherSubscriber;
import com.udm.common.domain.event.DomainEventPublisher;
import com.udm.common.domain.event.EventPublisher;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * Provides a CDI implementation for {@link com.udm.common.domain.event.DomainEventPublisher}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Singleton
public class DomainEventPublisherImpl extends DomainEventPublisher {

    private static final ThreadLocal<EventPublisher> LOCAL = new ThreadLocal<EventPublisher>();
    private final Provider<JpaPublisherSubscriber> provider;

    @Inject
    public DomainEventPublisherImpl(Provider<JpaPublisherSubscriber> provider) {
        assertArgumentNotNull(provider, "Publisher cannot be null.");
        this.provider = provider;
        DomainEventPublisher.domainEventPublisher = this;
    }

    @Override
    protected EventPublisher eventPublisher() {
        if (LOCAL.get() == null) {
            LOCAL.set(provider.get());
        }
        return LOCAL.get();
    }
}
