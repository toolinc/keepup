// Copyright 2014 University of Detroit Mercy.

package com.udm.health.persistence.model.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.udm.health.persistence.model.ModelBuilder;
import com.udm.health.persistence.validation.PositiveInteger;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Specifies a blood pressure measurement.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@Table(name = "BloodPressureMeasurement")
@PrimaryKeyJoinColumn(name = "idBloodPressureMeasurement", referencedColumnName = "idMeasurement")
public class BloodPressureMeasurement extends Measurement {

    @Deprecated
    public BloodPressureMeasurement() {
    }

    private BloodPressureMeasurement(Builder builder) {
        setId(builder.id);
        setPatient(builder.patient);
        setDate(builder.date);
        setMeasurementSystem(builder.measurementSystem);
        setUnit(builder.unit);
        setSystolic(builder.systolic);
        setDiastolic(builder.diastolic);
    }

    @NotNull
    @Column(name = "measurementSystem", length = 10, nullable = false)
    private MeasurementSystems measurementSystem;

    @Column(name = "measurementUnit", length = 10, nullable = false)
    private String unit;

    @PositiveInteger
    @Column(name = "systolic", nullable = false)
    private Integer systolic;

    @PositiveInteger
    @Column(name = "diastolic", nullable = false)
    private Integer diastolic;

    public MeasurementSystems getMeasurementSystem() {
        return measurementSystem;
    }

    public void setMeasurementSystem(MeasurementSystems measurementSystem) {
        this.measurementSystem = checkNotNull(measurementSystem);
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        checkState(!isNullOrEmpty(unit));
        this.unit = unit;
    }

    public Integer getSystolic() {
        return systolic;
    }

    public void setSystolic(Integer systolic) {
        this.systolic = checkNotNull(systolic);
    }

    public Integer getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(Integer diastolic) {
        this.diastolic = checkNotNull(diastolic);
    }

    /**
     * Builder of {@link com.udm.health.persistence.model.domain.BloodPressureMeasurement}
     * instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder implements ModelBuilder<BloodPressureMeasurement> {

        private UUID id;
        private Patient patient;
        private Date date;
        private MeasurementSystems measurementSystem;
        private String unit;
        private Integer systolic;
        private Integer diastolic;

        private Builder() {
            setUnit(USSystemUnits.mmHg);
        }

        public Builder setPatient(Patient patient) {
            this.patient = checkNotNull(patient);
            return this;
        }

        public Builder setDate(Date date) {
            this.date = checkNotNull(date);
            return this;
        }

        private void setMeasurementSystem(MeasurementSystems measurementSystem) {
            this.measurementSystem = checkNotNull(measurementSystem);
            unit = null;
        }

        public Builder setUnit(InternationalSystemUnits unit) {
            setMeasurementSystem(measurementSystem = MeasurementSystems.IS);
            checkNotNull(unit);
            checkState(!InternationalSystemUnits.mmHg.equals(unit));
            setUnit(unit.toString());
            return this;
        }

        public Builder setUnit(USSystemUnits unit) {
            setMeasurementSystem(measurementSystem = MeasurementSystems.USS);
            checkNotNull(unit);
            checkState(!USSystemUnits.mmHg.equals(unit));
            setUnit(unit.toString());
            return this;
        }

        private void setUnit(String unit) {
            checkState(!isNullOrEmpty(unit));
            this.unit = unit;
        }

        public Builder setSystolic(Integer systolic) {
            checkNotNull(systolic);
            checkState(systolic > -1);
            this.systolic = systolic;
            return this;
        }

        public Builder setDiastolic(Integer diastolic) {
            checkNotNull(diastolic);
            checkState(diastolic > -1);
            this.diastolic = diastolic;
            return this;
        }

        /**
         * Creates a instances of
         * {@link com.udm.health.persistence.model.domain.BloodPressureMeasurement} given the
         * specified characteristics on the
         * {@link com.udm.health.persistence.model.domain.BloodPressureMeasurement.Builder}
         *
         * @return a new instance
         *         {@link com.udm.health.persistence.model.domain.BloodPressureMeasurement}
         */
        @Override
        public BloodPressureMeasurement build() {
            id = UUID.randomUUID();
            return new BloodPressureMeasurement(this);
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         *         {@link com.udm.health.persistence.model.domain.BloodPressureMeasurement.Builder}
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
