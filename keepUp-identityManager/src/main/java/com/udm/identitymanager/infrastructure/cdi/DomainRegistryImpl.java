// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.infrastructure.cdi;

import com.udm.identitymanager.domain.DomainRegistry;
import com.udm.identitymanager.domain.service.identity.EncryptionService;
import com.udm.identitymanager.domain.service.identity.PasswordService;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * Provides a CDI implementation for {@link com.udm.identitymanager.domain.DomainRegistry}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Singleton
public class DomainRegistryImpl extends DomainRegistry {

    private final Provider<EncryptionService> encryptionServiceProvider;
    private final Provider<PasswordService> passwordServiceProvider;

    @Inject
    public DomainRegistryImpl(Provider<EncryptionService> encryptionServiceProvider,
                                    Provider<PasswordService> passwordServiceProvider) {
        assertArgumentNotNull(encryptionServiceProvider, "EncryptionService cannot be null.");
        assertArgumentNotNull(passwordServiceProvider, "PasswordService cannot be null.");
        this.encryptionServiceProvider = encryptionServiceProvider;
        this.passwordServiceProvider = passwordServiceProvider;
        DomainRegistry.domainRegistry = this;
    }

    @Override
    protected EncryptionService getEncryptionService() {
        return encryptionServiceProvider.get();
    }

    @Override
    protected PasswordService getPasswordService() {
        return passwordServiceProvider.get();
    }
}
