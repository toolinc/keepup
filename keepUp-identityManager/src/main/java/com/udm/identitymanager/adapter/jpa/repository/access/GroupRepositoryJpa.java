// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.adapter.jpa.repository.access;

import static javax.transaction.Transactional.TxType.REQUIRED;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.event.DomainEventPublisher;
import com.udm.common.domain.repository.Repository;
import com.udm.identitymanager.domain.event.access.GroupRegistered;
import com.udm.identitymanager.domain.model.access.Group;
import com.udm.identitymanager.domain.repository.access.GroupRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Specifies the jpa implementation for the
 * {@link com.udm.identitymanager.domain.repository.access.GroupRepository}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class GroupRepositoryJpa extends AssertionConcern implements GroupRepository {

    private final Repository<Group> repository;
    private final DomainEventPublisher domainEventPublisher;

    @Inject
    public GroupRepositoryJpa(Repository<Group> groupRepository,
                              DomainEventPublisher domainEventPublisher) {
        assertArgumentNotNull(groupRepository, "Repository cannot be null.");
        assertArgumentNotNull(domainEventPublisher, "Publisher cannot be null.");
        this.repository = groupRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Transactional(REQUIRED)
    @Override
    public void create(Group group) {
        repository.create(group);
        domainEventPublisher.publish(new GroupRegistered(group.getName()));
    }
}
