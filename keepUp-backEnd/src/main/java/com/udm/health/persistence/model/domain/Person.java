// Copyright 2014 University of Detroit Mercy.

package com.udm.health.persistence.model.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.LAZY;

import com.udm.health.persistence.model.Model;
import com.udm.health.persistence.validation.PersonMiddleName;
import com.udm.health.persistence.validation.PersonName;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Represents a person.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@Table(name = "Person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type", discriminatorType = DiscriminatorType.STRING, length = 50)
public abstract class Person extends Model {

    @NotNull
    @Id
    @Column(name = "idPerson")
    private UUID id;

    @PersonName
    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @PersonMiddleName
    @Column(name = "middleName", length = 2)
    private String middleName;

    @PersonName(message = "{com.udm.health.persistence.validation.PersonLastName.message}")
    @Column(name = "lastName", length = 45, nullable = false)
    private String lastName;

    @OneToOne(mappedBy = "person", cascade = {PERSIST, REFRESH, MERGE}, fetch = LAZY)
    private PersonAddress personAddress;

    @OneToOne(mappedBy = "person", cascade = {PERSIST, REFRESH, MERGE}, fetch = LAZY)
    private PersonContactInformation personContactInformation;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = checkNotNull(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkState(!isNullOrEmpty(name));
        this.name = name.toUpperCase();
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if (!isNullOrEmpty(middleName)) {
            middleName = middleName.toUpperCase();
        }
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        checkState(!isNullOrEmpty(lastName));
        this.lastName = lastName.toUpperCase();
    }

    public PersonAddress getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(PersonAddress personAddress) {
        this.personAddress = checkNotNull(personAddress);
    }

    public PersonContactInformation getPersonContactInformation() {
        return personContactInformation;
    }

    public void setPersonContactInformation(PersonContactInformation personContactInformation) {
        this.personContactInformation = personContactInformation;
    }
}
