// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.adapter.jpa.repository.identity;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import com.udm.common.AssertionConcern;
import com.udm.common.adapter.jpa.repository.QueryHelper;
import com.udm.common.domain.event.DomainEventPublisher;
import com.udm.common.domain.repository.Repository;
import com.udm.identitymanager.domain.event.identity.UserRegistered;
import com.udm.identitymanager.domain.model.identity.User;
import com.udm.identitymanager.domain.model.identity.User_;
import com.udm.identitymanager.domain.repository.identity.UserRepository;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

/**
 * Specifies the jpa implementation for the
 * {@link com.udm.identitymanager.domain.repository.identity.UserRepository}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class UserRepositoryJpa extends AssertionConcern implements UserRepository {

    private final Repository<User> repository;
    private final DomainEventPublisher domainEventPublisher;

    @Inject
    public UserRepositoryJpa(Repository<User> repository,
                             DomainEventPublisher domainEventPublisher) {
        assertArgumentNotNull(repository, "JpaRepository cannot be null.");
        assertArgumentNotNull(domainEventPublisher, "Publisher cannot be null.");
        this.repository = repository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Transactional(REQUIRED)
    @Override
    public void create(User user) {
        repository.create(user);
        domainEventPublisher.publish(new UserRegistered(user.getUserName()));
    }

    @Transactional(SUPPORTS)
    @Override
    public User userWithUserName(String userName) {
        User user = null;
        QueryHelper<User, User> qh = repository.newQueryHelper();
        try {
            qh.getQuery().where(qh.getBuilder().equal(qh.getRoot().get(User_.userName), userName));
            user = qh.getSingleResult();
        } catch (NoResultException exc) {
        }
        return user;
    }
}
