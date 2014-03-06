// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.infrastructure.cdi;

import com.udm.common.adapter.jpa.repository.JpaRepository;
import com.udm.common.domain.event.DomainEventPublisher;
import com.udm.common.adapter.jpa.event.Event;
import com.udm.common.domain.repository.Repository;
import com.udm.identitymanager.domain.DomainRegistry;
import com.udm.identitymanager.domain.model.access.Group;
import com.udm.identitymanager.domain.model.identity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Specifies the injection producer of the persistence module.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@ApplicationScoped
public class IdentityManagerModuleTest {


    private static final String PERSISTENCE_UNIT = "keepUpIdentityManagerPUTest";

    @PersistenceContext(unitName = PERSISTENCE_UNIT)
    private EntityManager entityManager;

    @Inject
    DomainEventPublisher domainEventPublisher;

    @Inject
    private DomainRegistry domainRegistry;

    @Produces
    public EntityManager produceEntityManager() {
        return entityManager;
    }

    @Produces
    public Repository<Event> produceEventRepository(EntityManager entityManager) {
        return new JpaRepository<Event>(entityManager) {
        };
    }

    @Produces
    public Repository<User> produceUserRepository(EntityManager entityManager) {
        return new JpaRepository<User>(entityManager) {
        };
    }

    @Produces
    public Repository<Group> producesGroupRepository(EntityManager entityManager) {
        return new JpaRepository<Group>(entityManager) {
        };
    }
}