// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.adapter.jpa.repository.identity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.udm.identitymanager.PersistenceTest;
import com.udm.identitymanager.domain.model.identity.ContactInformation;
import com.udm.identitymanager.domain.model.identity.EmailAddress;
import com.udm.identitymanager.domain.model.identity.Enablement;
import com.udm.identitymanager.domain.model.identity.Gender;
import com.udm.identitymanager.domain.model.identity.Person;
import com.udm.identitymanager.domain.model.identity.PersonUser;
import com.udm.identitymanager.domain.model.identity.TelephoneNumber;
import com.udm.identitymanager.domain.repository.identity.UserRepository;
import com.udm.identitymanager.domain.service.identity.EncryptionService;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.inject.Inject;

/**
 * Test for the class
 * {@link com.udm.identitymanager.adapter.jpa.repository.identity.PersonUserRepositoryJpa}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonUserRepositoryJpaTest extends PersistenceTest {

    @Inject
    private EncryptionService encryptionService;

    @Inject
    private UserRepository<PersonUser> instance;

    @Test
    public void shouldCreateUser() throws Exception {
        String userName = "newUserName";
        String pass = "9t43si8d7ajs9d0j!";
        String name = "John";
        String lastName = "Smith";
        Date dob = new GregorianCalendar(1978, 02, 04).getTime();
        String email = String.format("%s.%s@hotmail.com", name.toLowerCase(),
                lastName.toLowerCase());
        String phone = "8034523123";
        PersonUser user = PersonUser.Builder.newBuilder()
                .setUserName(userName)
                .setPassword(pass)
                .setEnablement(Enablement.indefiniteEnablement())
                .setPerson(
                        Person.Builder.newBuilder()
                                .setName(name)
                                .setLastName(lastName)
                                .setGender(Gender.MALE)
                                .setDateOfBirth(dob)
                                .setContactInformation(
                                        new ContactInformation(
                                                new EmailAddress(email),
                                                new TelephoneNumber(phone)
                                        )
                                ).build()
                ).build();
        instance.create(user);
        assertNotNull(user);
        assertEquals(userName, user.getUserName());
        assertEquals(encryptionService.encrypt(pass), user.getPassword());
        assertEquals(Enablement.indefiniteEnablement(), user.getEnablement());
    }

    @Test
    public void shouldNotFoundUserName() {
        assertNull(instance.userWithUserName("new"));
    }
}
