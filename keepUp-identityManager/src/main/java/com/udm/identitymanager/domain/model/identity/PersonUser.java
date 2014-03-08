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
 * Represents an actual person that is a user of the system.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@DiscriminatorValue("PERSON")
public class PersonUser extends User {

    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "idPerson", referencedColumnName = "idPerson",
            foreignKey = @ForeignKey(name = "user_person_fk"))
    private Person person;

    @Deprecated
    public PersonUser() {
    }

    private PersonUser(Builder builder) {
        setId(builder.id);
        setUserName(builder.userName);
        setPassword(builder.password);
        setPerson(builder.person);
        setEnablement(builder.enablement);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        assertArgumentNotNull(person, "The given person cannot be null.");
        this.person = person;
    }

    /**
     * Builder of {@link com.udm.identitymanager.domain.model.identity.PersonUser} instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder extends AssertionConcern implements
            DomainObjectBuilder<PersonUser> {

        private UUID id;
        private String userName;
        private String password;
        private Enablement enablement;
        private Person person;

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

        public Builder setPerson(Person person) {
            assertArgumentNotNull(person, "The given person cannot be null.");
            this.person = person;
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
         * Creates a instances of {@link com.udm.identitymanager.domain.model.identity.PersonUser}
         * given the specified characteristics on the
         * {@link com.udm.identitymanager.domain.model.identity.PersonUser.Builder}.
         *
         * @return a new instance {@link com.udm.identitymanager.domain.model.identity.PersonUser}
         */
        @Override
        public PersonUser build() {
            id = UUID.randomUUID();
            return new PersonUser(this);
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         * {@link com.udm.identitymanager.domain.model.identity.PersonUser.Builder}
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
