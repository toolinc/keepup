// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.resource;

import com.udm.common.AssertionConcern;
import com.udm.identitymanager.application.IdentityApplicationService;
import com.udm.identitymanager.application.command.RegisterPersonUserCommand;
import com.udm.identitymanager.domain.IdentityManagementException;
import com.udm.identitymanager.domain.model.identity.PersonUser;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Identity resource to interact with other SOA systems.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Path("/users/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IdentityResource extends AssertionConcern {

    @Inject
    private IdentityApplicationService identityApplicationService;

    @POST
    @Path("/register/person")
    public boolean registerPersonUser(RegisterPersonUserCommand registerPersonUserCommand) throws
            IdentityManagementException {
        PersonUser personUser =
                identityApplicationService.registerPersonUser(registerPersonUserCommand);
        return true;
    }
}
