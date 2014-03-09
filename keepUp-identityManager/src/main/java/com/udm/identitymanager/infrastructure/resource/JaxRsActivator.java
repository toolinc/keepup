// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.infrastructure.resource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Specifies the JAX-RS activator for the identity manager module.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@ApplicationPath("/identityManagement")
public class JaxRsActivator extends Application {
}
