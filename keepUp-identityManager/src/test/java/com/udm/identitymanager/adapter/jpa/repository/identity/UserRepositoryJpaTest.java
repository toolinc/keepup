// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.adapter.jpa.repository.identity;

import static org.junit.Assert.assertNull;

import com.udm.identitymanager.PersistenceTest;
import com.udm.identitymanager.domain.repository.identity.UserRepository;

import org.junit.Test;

import javax.inject.Inject;

/**
 * Test for the class
 * {@link com.udm.identitymanager.adapter.jpa.repository.identity.UserRepositoryJpa}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class UserRepositoryJpaTest extends PersistenceTest {

    @Inject
    private UserRepository instance;

    @Test
    public void shouldNotFoundUserName() {
        assertNull(instance.userWithUserName("new"));
    }
}
