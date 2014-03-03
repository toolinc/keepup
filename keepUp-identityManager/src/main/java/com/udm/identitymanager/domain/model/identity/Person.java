// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.model.identity;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.model.DomainObjectBuilder;
import com.udm.common.domain.model.DomainObjectConcurrencySafe;
import com.udm.identitymanager.domain.validation.DateOfBirth;
import com.udm.identitymanager.domain.validation.PersonName;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Represents a person.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@Table(name = "Person")
public class Person extends DomainObjectConcurrencySafe {

    @NotNull
    @Id
    @Column(name = "idPerson")
    private UUID id;

    @PersonName
    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @PersonName
    @Column(name = "lastName", length = 45, nullable = false)
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @DateOfBirth
    @Column(name = "dateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @NotNull
    @Embedded
    private ContactInformation contactInformation;

    @Deprecated
    public Person() {
    }

    private Person(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setLastName(builder.lastName);
        setGender(builder.gender);
        setDateOfBirth(builder.dateOfBirth);
        setContactInformation(builder.contactInformation);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        assertArgumentNotNull(id, "The id cannot be null.");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        assertArgumentNotEmpty(name, "The name cannot be empty or null.");
        this.name = name.toUpperCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        assertArgumentNotEmpty(lastName, "The last name cannot be empty or null.");
        this.lastName = lastName.toUpperCase();
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        assertArgumentNotNull(gender, "The gender cannot be null.");
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        assertArgumentNotNull(dateOfBirth, "The date of birth cannot be null.");
        this.dateOfBirth = dateOfBirth;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        assertArgumentNotNull(contactInformation, "The contact information cannot be null.");
        this.contactInformation = contactInformation;
    }

    /**
     * Builder of {@link com.udm.identitymanager.domain.model.identity.Person} instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder extends AssertionConcern implements
            DomainObjectBuilder<Person> {

        private UUID id;
        private String name;
        private String lastName;
        private Gender gender;
        private Date dateOfBirth;
        private ContactInformation contactInformation;

        private Builder() {
        }

        public Builder setName(String name) {
            assertArgumentNotEmpty(name, "The name cannot be empty or null.");
            this.name = name.toUpperCase();
            return this;
        }

        public Builder setLastName(String lastName) {
            assertArgumentNotEmpty(lastName, "The last name cannot be empty or null.");
            this.lastName = lastName.toUpperCase();
            return this;
        }

        public Builder setGender(Gender gender) {
            assertArgumentNotNull(gender, "The gender cannot be null.");
            this.gender = gender;
            return this;
        }

        public Builder setDateOfBirth(Date dateOfBirth) {
            assertArgumentNotNull(dateOfBirth, "The date of birth cannot be null.");
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setContactInformation(ContactInformation contactInformation) {
            assertArgumentNotNull(contactInformation, "The contact information cannot be null.");
            this.contactInformation = contactInformation;
            return this;
        }

        /**
         * Creates a instances of
         * {@link com.udm.identitymanager.domain.model.identity.Person} given the
         * specified characteristics on the
         * {@link com.udm.identitymanager.domain.model.identity.Person.Builder}.
         *
         * @return a new instance
         *         {@link com.udm.identitymanager.domain.model.identity.Person}
         */
        @Override
        public Person build() {
            id = UUID.randomUUID();
            return new Person(this);
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         *         {@link com.udm.identitymanager.domain.model.identity.Person.Builder}
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
