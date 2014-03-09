// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.resource;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.udm.identitymanager.PersistenceTest;
import com.udm.identitymanager.application.command.RegisterPersonUserCommand;
import com.udm.identitymanager.infrastructure.resource.GsonProvider;
import com.udm.identitymanager.infrastructure.resource.JaxRsActivator;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Before;
import org.junit.Test;

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
public class IdentityResourceTest extends PersistenceTest {
    private static final String RESOURCE_PREFIX = JaxRsActivator.class
            .getAnnotation(ApplicationPath.class).value().substring(1) + "/users/";

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
    public void testGetCustomerByIdUsingClientRequest() throws Exception {
        RegisterPersonUserCommand command = new RegisterPersonUserCommand("restClientPersonUser",
                "9!2swT59320", "Kit", "Temple", "FEMALE",
                new GregorianCalendar(1970, 5, 26).getTime(), true, null, null,
                "kit.temple@gmail.com", "1234567890");
        Response response = target.path("register/person")
                .request()
                .post(Entity.entity(command, mediaType), Response.class);
        assertThat(response.getStatus(), is(200));
        assertTrue(response.readEntity(Boolean.class));
    }
}
