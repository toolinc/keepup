// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.adapter.jpa.repository.access;

import static javax.transaction.Transactional.TxType.SUPPORTS;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.repository.Repository;
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

    @Inject
    public GroupRepositoryJpa(Repository<Group> groupRepository) {
        assertArgumentNotNull(groupRepository, "Repository cannot be null.");
        this.repository = groupRepository;
    }

    @Transactional(SUPPORTS)
    @Override
    public void create(Group group) {
        repository.create(group);
    }
}
