// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.resource;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.udm.identitymanager.PersistenceTest;
import com.udm.identitymanager.application.command.ChangeUserPasswordCommand;
import com.udm.identitymanager.application.command.RegisterPersonUserCommand;
import com.udm.identitymanager.application.command.RegisterSystemUserCommand;
import com.udm.identitymanager.infrastructure.resource.GsonProvider;
import com.udm.identitymanager.infrastructure.resource.JaxRsActivator;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.net.URL;
import java.util.GregorianCalendar;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Test for {@link com.udm.identitymanager.resource.IdentityResource}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@RunAsClient
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IdentityResourceTest extends PersistenceTest {
    private static final String RESOURCE_PREFIX = JaxRsActivator.class
            .getAnnotation(ApplicationPath.class).value().substring(1) + "/user/";

    private final String mediaType = MediaType.APPLICATION_JSON;
    @ArquillianResource private URL deploymentUrl;
    private Client client;
    private WebTarget target;

    @Before
    public void initClient() {
        client = ClientBuilder.newClient().register(GsonProvider.class);
        target = this.client.target(deploymentUrl.toString() + RESOURCE_PREFIX);
    }

    @Test
    public void shouldRegisterPersonUser() throws Exception {
        RegisterPersonUserCommand command = new RegisterPersonUserCommand("restPersonUser",
                "1970#swT59526", "Kit", "Temple", "FEMALE",
                new GregorianCalendar(1970, 5, 26).getTime(), true, null, null,
                "kit.temple@gmail.com", "1234567890");
        Response response = target.path("register/person")
                .request()
                .post(Entity.entity(command, mediaType), Response.class);
        assertThat(response.getStatus(), is(200));
        assertTrue(response.readEntity(Boolean.class));
    }

    @Test
    public void shouldRegisterSystemUser() throws Exception {
        RegisterSystemUserCommand command = new RegisterSystemUserCommand("restSystemUser",
                "1995!2$%^Gj59326", "restSystem", "Tim", "OGrady", "FEMALE",
                new GregorianCalendar(1995, 3, 26).getTime(), true, null, null,
                "tim.ogrady@gmail.com", "1234567890");
        Response response = target.path("register/system")
                .request()
                .post(Entity.entity(command, mediaType), Response.class);
        assertThat(response.getStatus(), is(200));
        assertTrue(response.readEntity(Boolean.class));
    }

    @Test
    public void shouldChangeUserPassword() throws Exception {
        String userName = "restfulSystemUser";
        String password = "1984!@#$kQwAXz$";
        String newPassword = "newPass#1984!@#$kQwAXz$";
        RegisterSystemUserCommand command = new RegisterSystemUserCommand(userName,
                password, "newly rest System", "Tim", "Ford", "MALE",
                new GregorianCalendar(1984, 9, 1).getTime(), true, null, null,
                "tim.ford@gmail.com", "1234567890");
        Response response = target.path("register/system")
                .request()
                .post(Entity.entity(command, mediaType), Response.class);
        assertThat(response.getStatus(), is(200));
        assertTrue(response.readEntity(Boolean.class));
        ChangeUserPasswordCommand passwdCommand = new ChangeUserPasswordCommand(userName, password,
                newPassword);
        response = target.path("changePassword")
                .request()
                .post(Entity.entity(passwdCommand, mediaType), Response.class);
        assertThat(response.getStatus(), is(200));
        assertTrue(response.readEntity(Boolean.class));
    }
}
