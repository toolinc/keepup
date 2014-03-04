// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.infrastructure.inject;

import com.udm.common.adapter.jpa.repository.GenericRepository;
import com.udm.common.domain.repository.Repository;
import com.udm.identitymanager.domain.model.access.Group;
import com.udm.identitymanager.domain.model.identity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
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

    @Produces
    public EntityManager produceEntityManager() {
        return entityManager;
    }

    public void closeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @Produces
    public Repository<User> produceUserRepository(EntityManager entityManager) {
        return new GenericRepository<User>(entityManager) {
        };
    }

    @Produces
    public Repository<Group> producesGroupRepository(EntityManager entityManager) {
        return new GenericRepository<Group>(entityManager) {
        };
    }
}