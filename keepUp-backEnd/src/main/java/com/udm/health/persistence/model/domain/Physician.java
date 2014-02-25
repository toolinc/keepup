// Copyright 2014 University of Detroit Mercy.

package com.udm.health.persistence.model.domain;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.udm.health.persistence.model.ModelBuilder;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Represents a physician.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@DiscriminatorValue("PHYSICIAN")
public class Physician extends Person {

    @Deprecated
    public Physician() {
    }

    private Physician(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setMiddleName(builder.middleName);
        setLastName(builder.lastName);
    }

    /**
     * Builder of {@link com.udm.health.persistence.model.domain.Physician} instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder implements ModelBuilder<Physician> {

        private UUID id;
        private String name;
        private String middleName;
        private String lastName;
        private final PersonAddress.Builder address = PersonAddress.Builder.newBuilder();
        private PersonContactInformation.Builder contactInformation;

        private Builder() {
        }

        public Builder setName(String name) {
            checkState(!isNullOrEmpty(name));
            this.name = name.toUpperCase();
            return this;
        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder setLastName(String lastName) {
            checkState(!isNullOrEmpty(lastName));
            this.lastName = lastName.toUpperCase();
            return this;
        }

        public PersonAddress.Builder getAddress() {
            return address;
        }

        public PersonContactInformation.Builder getContactInformation() {
            if (contactInformation == null) {
                contactInformation = PersonContactInformation.Builder.newBuilder();
            }
            return contactInformation;
        }

        /**
         * Creates a instances of {@link com.udm.health.persistence.model.domain.Physician} given
         * the specified characteristics on the
         * {@link com.udm.health.persistence.model.domain.Physician.Builder}.
         *
         * @return a new instance {@link com.udm.health.persistence.model.domain.Physician}
         */
        @Override
        public Physician build() {
            id = UUID.randomUUID();
            Physician physician = new Physician(this);
            address.setPerson(physician);
            physician.setPersonAddress(address.build());
            if (contactInformation != null) {
                contactInformation.setPerson(physician);
                physician.setPersonContactInformation(contactInformation.build());
            }
            return physician;
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         *         {@link com.udm.health.persistence.model.domain.Physician.Builder}.
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
