// Copyright 2014 University of Detroit Mercy.

package com.udm.health.domain.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

import com.udm.common.domain.model.DomainObjectBuilder;
import com.udm.common.domain.model.DomainObjectConcurrencySafe;
import com.udm.health.validation.Email;
import com.udm.health.validation.Telephone;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents the contact information of a person.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@Table(name = "PersonContactInformation")
public class PersonContactInformation extends DomainObjectConcurrencySafe {

    @Deprecated
    public PersonContactInformation() {
    }

    private PersonContactInformation(Builder builder) {
        setId(builder.id);
        setPerson(builder.person);
        setEmail(builder.email);
        setTelephone(builder.telephone);
    }

    @NotNull
    @Id
    @Column(name = "idPersonContactInformation")
    private UUID id;

    @NotNull(message = "{com.udm.health.persistence.validation.Person.message}")
    @OneToOne(cascade = {PERSIST, MERGE}, fetch = LAZY)
    @JoinColumn(name = "idPerson", nullable = false, unique = true,
            foreignKey = @ForeignKey(name = "personContactInformationFK"))
    private Person person;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Telephone
    @Column(name = "telephone", length = 15)
    private String telephone;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = checkNotNull(id);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = checkNotNull(person);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        checkState(!isNullOrEmpty(email));
        this.email = email.toUpperCase();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        checkState(!isNullOrEmpty(telephone));
        this.telephone = telephone;
    }

    /**
     * Builder of {@link com.udm.health.domain.model.PersonContactInformation}
     * instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder implements DomainObjectBuilder<PersonContactInformation> {

        private UUID id;
        private Person person;
        private String email;
        private String telephone;

        private Builder() {
        }

        public Builder setPerson(Person person) {
            this.person = checkNotNull(person);
            return this;
        }

        public Builder setEmail(String email) {
            checkState(!isNullOrEmpty(email));
            this.email = email.toUpperCase();
            return this;
        }

        public Builder setTelephone(String telephone) {
            checkState(!isNullOrEmpty(telephone));
            this.telephone = telephone.toUpperCase();
            return this;
        }

        /**
         * Creates a instances of
         * {@link com.udm.health.domain.model.PersonContactInformation} given the
         * specified characteristics on the
         * {@link com.udm.health.domain.model.PersonContactInformation.Builder}.
         *
         * @return a new instance
         *         {@link com.udm.health.domain.model.PersonContactInformation}
         */
        @Override
        public PersonContactInformation build() {
            id = UUID.randomUUID();
            return new PersonContactInformation(this);
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         *         {@link com.udm.health.domain.model.PersonContactInformation.Builder}
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
