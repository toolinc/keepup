// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.adapter.jpa.repository.access;

import com.udm.identitymanager.PersistenceTest;
import com.udm.identitymanager.domain.model.access.Group;
import com.udm.identitymanager.domain.repository.access.GroupRepository;

import org.junit.Test;

import javax.inject.Inject;

/**
 * Test for the class
 * {@link com.udm.identitymanager.adapter.jpa.repository.access.GroupRepositoryJpa}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class GroupRepositoryJpaTest extends PersistenceTest {

    @Inject
    private GroupRepository instance;

    @Test
    public void shouldCreateGroup() throws Exception {
        Group group = Group.Builder.newBuilder().setName("new test group").build();
        instance.create(group);
    }
}
