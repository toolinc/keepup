// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.infrastructure.resource;

import com.udm.identitymanager.domain.IdentityManagementException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Provides the appropriate response for
 * {@link com.udm.identitymanager.domain.IdentityManagementException}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Provider
public class IdentityManagementExceptionMapper implements
        ExceptionMapper<IdentityManagementException> {

    @Override
    public Response toResponse(IdentityManagementException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
