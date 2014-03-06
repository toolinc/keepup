// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.model.identity;

import com.udm.common.AssertionConcern;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Represents the enable state of a entity.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Embeddable
public class Enablement extends AssertionConcern implements Serializable {

    @Temporal(TemporalType.DATE)
    @Column(name = "getStartDate")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "getEndDate")
    private Date endDate;

    @NotNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Deprecated
    public Enablement() {
    }

    public static Enablement indefiniteEnablement() {
        return new Enablement(Boolean.TRUE, null, null);
    }

    public Enablement(boolean anEnabled, Date aStartDate, Date anEndDate) {
        super();
        assertArgumentNotNull(anEnabled, "A State should be provided enable or disable.");
        if (aStartDate != null || anEndDate != null) {
            assertArgumentNotNull(aStartDate, "The start date must be provided.");
            assertArgumentNotNull(anEndDate, "The end date must be provided.");
            assertArgumentFalse(aStartDate.after(anEndDate),
                    "Enablement start and/or end date is invalid.");
        }
        this.enabled = anEnabled;
        if (aStartDate != null) {
            this.startDate = newDate(aStartDate);
        }
        if (anEndDate != null) {
            this.endDate = newDate(anEndDate);
        }
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public Date getStartDate() {
        if (startDate != null) {
            return newDate(startDate);
        }
        return null;
    }

    public Date getEndDate() {
        if (endDate != null) {
            return newDate(endDate);
        }
        return null;
    }

    /**
     * Specifies whether or not the specified start date and end date have expired.
     *
     * @return true if the system date is not between the start date and end date, otherwise false.
     */
    public boolean isTimeExpired() {
        boolean timeExpired = false;
        if (getStartDate() != null && getEndDate() != null) {
            Date now = new Date();
            if (now.before(getStartDate()) || now.after(getEndDate())) {
                timeExpired = true;
            }
        }
        return timeExpired;
    }

    /**
     * Specifies whether or not the state of an entity is enabled or not.
     *
     * @return true if the system date is between the start date and end date, otherwise false.
     */
    public boolean isStateEnabled() {
        boolean enabled = false;
        if (this.isEnabled() && !this.isTimeExpired()) {
            enabled = true;
        }
        return enabled;
    }

    @Override
    public boolean equals(Object anObject) {
        if (anObject == null || !Objects.equals(getClass(), anObject.getClass())) {
            return false;
        }
        final Enablement enablement = (Enablement) anObject;
        return Objects.equals(isEnabled(), enablement.isEnabled())
                && Objects.equals(getStartDate(), enablement.getStartDate())
                && Objects.equals(getEndDate(), enablement.getEndDate());
    }

    @Override
    public int hashCode() {
        int hash = 181;
        hash = 19563 * hash + Objects.hashCode(isEnabled()) + Objects.hashCode(getStartDate())
                + Objects.hashCode(getEndDate());
        return hash;
    }
}
