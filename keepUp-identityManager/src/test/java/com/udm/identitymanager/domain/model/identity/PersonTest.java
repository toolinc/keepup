// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.model.identity;

import com.udm.identitymanager.PersistenceTest;

import org.junit.Test;

import java.util.GregorianCalendar;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

/**
 * Test for the class {@link com.udm.identitymanager.domain.model.identity.Person}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class PersonTest extends PersistenceTest {

    @Inject
    private UserTransaction tx;

    @Test
    public void shouldCreatePerson() throws Exception {
        Person.Builder builder = Person.Builder.newBuilder();
        builder.setName("John")
                .setLastName("Doe")
                .setGender(Gender.MALE)
                .setDateOfBirth(new GregorianCalendar(1980, 1, 25).getTime())
                .setContactInformation(
                        new ContactInformation(
                                new EmailAddress("john.doe@gmail.com"),
                                new TelephoneNumber("5556367442")));
        tx.begin();
        em.joinTransaction();
        em.persist(builder.build());
        tx.commit();
    }
}
