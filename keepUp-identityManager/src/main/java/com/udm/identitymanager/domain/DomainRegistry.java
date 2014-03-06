// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain;

import com.udm.common.AssertionConcern;
import com.udm.identitymanager.domain.service.identity.EncryptionService;
import com.udm.identitymanager.domain.service.identity.PasswordService;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Serves as a repository of services.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public abstract class DomainRegistry extends AssertionConcern {

    protected static DomainRegistry domainRegistry;

    protected abstract EncryptionService getEncryptionService();

    protected abstract PasswordService getPasswordService();

    public static EncryptionService encryptionService() {
        return domainRegistry.getEncryptionService();
    }

    public static PasswordService passwordService() {
        return domainRegistry.getPasswordService();
    }
}
