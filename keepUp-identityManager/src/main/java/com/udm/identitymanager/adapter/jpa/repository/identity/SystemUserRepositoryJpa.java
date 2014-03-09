// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.adapter.jpa.repository.identity;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import com.udm.common.AssertionConcern;
import com.udm.common.adapter.jpa.repository.QueryHelper;
import com.udm.common.domain.event.DomainEventPublisher;
import com.udm.common.domain.repository.Repository;
import com.udm.identitymanager.domain.event.identity.UserRegistered;
import com.udm.identitymanager.domain.model.identity.SystemUser;
import com.udm.identitymanager.domain.model.identity.User_;
import com.udm.identitymanager.domain.repository.identity.UserRepository;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

/**
 * Specifies the jpa implementation for the
 * {@link com.udm.identitymanager.domain.repository.identity.UserRepository} for the
 * {@link com.udm.identitymanager.domain.model.identity.SystemUser}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class SystemUserRepositoryJpa extends AssertionConcern implements
        UserRepository<SystemUser> {

    private final Repository<SystemUser> repository;
    private final DomainEventPublisher domainEventPublisher;

    @Inject
    public SystemUserRepositoryJpa(Repository<SystemUser> repository,
                                   DomainEventPublisher domainEventPublisher) {
        assertArgumentNotNull(repository, "JpaRepository cannot be null.");
        assertArgumentNotNull(domainEventPublisher, "Publisher cannot be null.");
        this.repository = repository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Transactional(REQUIRED)
    @Override
    public void create(SystemUser user) {
        repository.create(user);
        domainEventPublisher.publish(new UserRegistered(user.getUserName()));
    }

    @Transactional(SUPPORTS)
    @Override
    public SystemUser userWithUserName(String userName) {
        SystemUser user = null;
        QueryHelper<SystemUser, SystemUser> qh = repository.newQueryHelper();
        try {
            qh.getQuery().where(qh.getBuilder().equal(qh.getRoot().get(User_.userName), userName));
            user = qh.getSingleResult();
        } catch (NoResultException exc) {
        }
        return user;
    }
}
