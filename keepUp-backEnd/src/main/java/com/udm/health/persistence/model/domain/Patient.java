// Copyright 2014 University of Detroit Mercy.

package com.udm.health.persistence.model.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.udm.health.persistence.model.ModelBuilder;
import com.udm.health.persistence.validation.SSN;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * Represents a patient.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends Person {

    @Deprecated
    public Patient() {
    }

    private Patient(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setMiddleName(builder.middleName);
        setLastName(builder.lastName);
        setGender(builder.gender);
        setDateOfBirth(builder.dateOfBirth);
        setSsn(builder.ssn);
        setPhysician(builder.physician);
    }

    @NotNull(message = "{com.udm.health.persistence.validation.Gender.message}")
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Past(message = "{com.udm.health.persistence.validation.DOB.message}")
    @Temporal(TemporalType.DATE)
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @SSN
    @Column(name = "ssn")
    private String ssn;

    @NotNull(message = "{com.udm.health.persistence.validation.Physician.message}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPhysician", referencedColumnName = "idPerson",
            foreignKey = @ForeignKey(name = "patientFK"))
    private Physician physician;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = checkNotNull(gender);
    }

    public Date getDateOfBirth() {
        return newDate(dateOfBirth);
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = checkNotNull(dateOfBirth);
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        checkState(!isNullOrEmpty(ssn));
        this.ssn = ssn;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = checkNotNull(physician);
    }

    /**
     * Builder of {@link com.udm.health.persistence.model.domain.Patient} instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder implements ModelBuilder<Patient> {

        private UUID id;
        private String name;
        private String middleName;
        private String lastName;
        private Gender gender;
        private Date dateOfBirth;
        private String ssn;
        private Physician physician;
        private final PersonAddress.Builder address = PersonAddress.Builder.newBuilder();
        private final PersonContactInformation.Builder contactInformation =
                PersonContactInformation.Builder.newBuilder();

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

        public Builder setGender(Gender gender) {
            this.gender = checkNotNull(gender);
            return this;
        }

        public Builder setDateOfBirth(int year, int month, int date) {
            this.dateOfBirth = checkNotNull(newDate(year, month, date));
            return this;
        }

        public Builder setSsn(String ssn) {
            checkState(!isNullOrEmpty(ssn));
            this.ssn = ssn.toUpperCase();
            return this;
        }

        public Builder setPhysician(Physician physician) {
            this.physician = checkNotNull(physician);
            return this;
        }

        public PersonAddress.Builder getAddress() {
            return address;
        }

        public PersonContactInformation.Builder getContactInformation() {
            return contactInformation;
        }

        /**
         * Creates a instances of {@link com.udm.health.persistence.model.domain.Patient} given
         * the specified characteristics on the
         * {@link com.udm.health.persistence.model.domain.Patient.Builder}.
         *
         * @return a new instance {@link com.udm.health.persistence.model.domain.Patient}
         */
        @Override
        public Patient build() {
            id = UUID.randomUUID();
            Patient patient = new Patient(this);
            address.setPerson(patient);
            patient.setPersonAddress(address.build());
            contactInformation.setPerson(patient);
            patient.setPersonContactInformation(contactInformation.build());
            return patient;
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         *         {@link com.udm.health.persistence.model.domain.Patient.Builder}.
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
