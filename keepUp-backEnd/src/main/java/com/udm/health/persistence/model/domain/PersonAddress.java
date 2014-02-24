// Copyright 2014 University of Detroit Mercy.

package com.udm.health.persistence.model.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.udm.health.persistence.model.Model;
import com.udm.health.persistence.model.ModelBuilder;
import com.udm.health.persistence.validation.City;
import com.udm.health.persistence.validation.Street;
import com.udm.health.persistence.validation.ZipCode;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents a person address.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@Table(name = "PersonAddress")
public class PersonAddress extends Model {

    @Deprecated
    public PersonAddress() {
    }

    private PersonAddress(Builder builder) {
        setId(builder.id);
        setPerson(builder.person);
        setStreet(builder.street);
        setCity(builder.city);
        setZipCode(builder.zipCode);
        setState(builder.state);
    }

    @Id
    @Column(name = "idPersonAddress")
    private UUID id;

    @NotNull(message = "{com.udm.health.persistence.validation.Person.message}")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPerson", nullable = false, unique = true,
            foreignKey = @ForeignKey(name = "personAddressFK"))
    private Person person;

    @Street
    @Column(name = "street", length = 255)
    private String street;

    @City
    @Column(name = "city", length = 50)
    private String city;

    @ZipCode
    @Column(name = "zipCode", length = 8)
    private String zipCode;

    @NotNull(message = "{com.udm.health.persistence.validation.State.message}")
    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = 45, nullable = false)
    private State state;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (!isNullOrEmpty(street)) {
            street = street.toUpperCase();
        }
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (!isNullOrEmpty(city)) {
            city = city.toUpperCase();
        }
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        if (!isNullOrEmpty(zipCode)) {
            zipCode = zipCode.toUpperCase();
        }
        this.zipCode = zipCode;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = checkNotNull(state);
    }

    /**
     * Builder of {@link com.udm.health.persistence.model.domain.PersonAddress} instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder implements ModelBuilder<PersonAddress> {

        private UUID id;
        private Person person;
        private String street;
        private String city;
        private String zipCode;
        private State state;

        private Builder() {
        }

        public Builder setPerson(Person person) {
            this.person = checkNotNull(person);
            return this;
        }

        public Builder setStreet(String street) {
            if (!isNullOrEmpty(street)) {
                street = street.toUpperCase();
            }
            this.street = street;
            return this;
        }

        public Builder setCity(String city) {
            if (!isNullOrEmpty(city)) {
                city = city.toUpperCase();
            }
            this.city = city;
            return this;
        }

        public Builder setZipCode(String zipCode) {
            if (!isNullOrEmpty(zipCode)) {
                zipCode = zipCode.toUpperCase();
            }
            this.zipCode = zipCode;
            return this;
        }

        public Builder setState(State state) {
            this.state = checkNotNull(state);
            return this;
        }

        /**
         * Creates a instances of {@link com.udm.health.persistence.model.domain.PersonAddress}
         * given the specified characteristics on the
         * {@link com.udm.health.persistence.model.domain.PersonAddress.Builder}
         *
         * @return a new instance {@link com.udm.health.persistence.model.domain.PersonAddress}
         */
        @Override
        public PersonAddress build() {
            id = UUID.randomUUID();
            return new PersonAddress(this);
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         *         {@link com.udm.health.persistence.model.domain.PersonAddress.Builder}.
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
