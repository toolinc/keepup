// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.model.identity;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.model.DomainObjectBuilder;
import com.udm.identitymanager.domain.DomainRegistry;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Represents an allowed system that is a user of the system in context.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@DiscriminatorValue("SYSTEM")
public class SystemUser extends User {

    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "idSystem", referencedColumnName = "idSystem",
            foreignKey = @ForeignKey(name = "user_system_fk"))
    private System system;

    @Deprecated
    public SystemUser() {
    }

    private SystemUser(Builder builder) {
        setId(builder.id);
        setUserName(builder.userName);
        setPassword(builder.password);
        setSystem(builder.system);
        setEnablement(builder.enablement);
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        assertArgumentNotNull(system, "The given system cannot be null.");
        this.system = system;
    }

    /**
     * Builder of {@link com.udm.identitymanager.domain.model.identity.SystemUser} instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder extends AssertionConcern implements
            DomainObjectBuilder<SystemUser> {

        private UUID id;
        private String userName;
        private String password;
        private Enablement enablement;
        private System system;

        private Builder() {
        }

        public Builder setUserName(String userName) {
            assertArgumentNotEmpty(userName, "The provided user name is null or empty.");
            this.userName = userName;
            return this;
        }

        public Builder setPassword(String password) {
            assertArgumentNotEmpty(password, "The provided password is null or empty.");
            assertPasswordNotWeak(password);
            assertUsernamePasswordNotSame(password);
            this.password = asEncryptedValue(password);
            return this;
        }

        public Builder setEnablement(Enablement enablement) {
            assertArgumentNotNull(enablement, "The provide enablement is null.");
            this.enablement = enablement;
            return this;
        }

        public Builder setSystem(System system) {
            assertArgumentNotNull(system, "The given system cannot be null.");
            this.system = system;
            return this;
        }

        private String asEncryptedValue(String aPlainTextPassword) {
            return DomainRegistry.encryptionService().encrypt(aPlainTextPassword);
        }

        private void assertPasswordNotWeak(String aPlainTextPassword) {
            this.assertArgumentFalse(DomainRegistry.passwordService().isWeak(aPlainTextPassword),
                    "The password must be stronger.");
        }

        private void assertUsernamePasswordNotSame(String aPlainTextPassword) {
            this.assertArgumentNotEquals(userName, aPlainTextPassword,
                    "The username and password must not be the same.");
        }

        /**
         * Creates a instances of {@link com.udm.identitymanager.domain.model.identity.SystemUser}
         * given the specified characteristics on the
         * {@link com.udm.identitymanager.domain.model.identity.SystemUser.Builder}.
         *
         * @return a new instance {@link com.udm.identitymanager.domain.model.identity.SystemUser}
         */
        @Override
        public SystemUser build() {
            id = UUID.randomUUID();
            return new SystemUser(this);
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         * {@link com.udm.identitymanager.domain.model.identity.SystemUser.Builder}
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
