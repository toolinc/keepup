// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.adapter.jpa.repository.identity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.udm.common.domain.event.DomainEventPublisher;
import com.udm.identitymanager.PersistenceTest;
import com.udm.identitymanager.domain.model.identity.ContactInformation;
import com.udm.identitymanager.domain.model.identity.EmailAddress;
import com.udm.identitymanager.domain.model.identity.Enablement;
import com.udm.identitymanager.domain.model.identity.Gender;
import com.udm.identitymanager.domain.model.identity.Person;
import com.udm.identitymanager.domain.model.identity.PersonUser;
import com.udm.identitymanager.domain.model.identity.TelephoneNumber;
import com.udm.identitymanager.domain.model.identity.User;
import com.udm.identitymanager.domain.repository.identity.UserRepository;
import com.udm.identitymanager.domain.service.identity.EncryptionService;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.GregorianCalendar;

import javax.inject.Inject;

/**
 * Test for the class
 * {@link com.udm.identitymanager.adapter.jpa.repository.identity.UserRepositoryJpa}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryJpaTest extends PersistenceTest {

    @Inject
    private EncryptionService encryptionService;

    @Inject
    private UserRepository instance;

    private final String pass = "9t43si8d7ajs9d0j!";

    @Test
    public void shouldCreateUser() throws Exception {
        User user = PersonUser.Builder.newBuilder()
                .setUserName("newUserName")
                .setPassword(pass)
                .setEnablement(Enablement.indefiniteEnablement())
                .setPerson(
                        Person.Builder.newBuilder()
                                .setName("Jr John")
                                .setLastName("Smith")
                                .setGender(Gender.MALE)
                                .setDateOfBirth(new GregorianCalendar(1980, 01, 01).getTime())
                                .setContactInformation(
                                        new ContactInformation(
                                                new EmailAddress("john.smith@hotmail.com"),
                                                new TelephoneNumber("8034523456")
                                        )
                                ).build()
                ).build();
        instance.create(user);
        assertNotNull(user);
        assertEquals("newUserName", user.getUserName());
        assertEquals(encryptionService.encrypt(pass), user.getPassword());
        assertEquals(Enablement.indefiniteEnablement(), user.getEnablement());
    }

    @Test
    public void shouldNotFoundUserName() {
        assertNull(instance.userWithUserName("new"));
    }
}
