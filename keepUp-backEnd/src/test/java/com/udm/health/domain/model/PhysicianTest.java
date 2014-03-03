// Copyright 2014 University of Detroit Mercy.

package com.udm.health.domain.model;

import com.udm.health.PersistenceTest;

import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

/**
 * Test for the class {@link com.udm.health.domain.model.Physician}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class PhysicianTest extends PersistenceTest {

    @Inject
    private EntityManager em;

    @Inject
    private UserTransaction tx;

    @Test
    public void shouldCreatePhysician() throws Exception {
        Physician.Builder builder = Physician.Builder.newBuilder();
        builder.setName("John")
                .setLastName("Doe")
                .setMiddleName("M")
                .getAddress()
                .setState(State.CA);
        Physician physician = builder.build();
        tx.begin();
        em.joinTransaction();
        em.persist(physician);
        tx.commit();
    }
}
