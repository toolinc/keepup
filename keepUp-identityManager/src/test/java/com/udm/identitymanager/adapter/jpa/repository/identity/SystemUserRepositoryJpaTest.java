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
import com.udm.identitymanager.domain.model.identity.System;
import com.udm.identitymanager.domain.model.identity.SystemUser;
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
 * {@link com.udm.identitymanager.adapter.jpa.repository.identity.SystemUserRepositoryJpa}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SystemUserRepositoryJpaTest extends PersistenceTest {

    @Inject
    private EncryptionService encryptionService;

    @Inject
    private UserRepository<SystemUser> instance;

    @Test
    public void shouldCreateUser() throws Exception {
        String userName = "systemArquillianTest";
        String pass = "a1l2f3a4!T!";
        String name = "Adam";
        String lastName = "Doe";
        String systemName = "keep up test arquillian user";
        Date dob = new GregorianCalendar(1984, 05, 20).getTime();
        String email = String.format("%s.%s@hotmail.com", name.toLowerCase(),
                lastName.toLowerCase());
        String phone = "8034523456";
        SystemUser user = SystemUser.Builder.newBuilder()
                .setUserName(userName)
                .setPassword(pass)
                .setEnablement(Enablement.indefiniteEnablement())
                .setSystem(
                        System.Builder.newBuilder()
                                .setName(systemName)
                                .setAdministrator(
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
