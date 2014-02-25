// Copyright 2014 University of Detroit Mercy.

package com.udm.health.persistence.model.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import com.udm.health.persistence.model.Model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

/**
 * Specifies a Measurement.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@Table(name = "Measurement")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Measurement extends Model {

    @Deprecated
    public Measurement() {
    }

    @Id
    @Column(name = "idMeasurement")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idPatient", referencedColumnName = "idPerson",
            foreignKey = @ForeignKey(name = "measurementFK"))
    private Patient patient;

    @Future
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateOfMeasurement")
    private Date date;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = checkNotNull(id);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = checkNotNull(patient);
    }

    public Date getDate() {
        return newDate(date);
    }

    public void setDate(Date date) {
        this.date = checkNotNull(date);
    }
}
