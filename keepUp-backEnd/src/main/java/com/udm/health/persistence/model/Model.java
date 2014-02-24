// Copyright 2014 University of Detroit Mercy.

package com.udm.health.persistence.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * This class represents a persistence entity. Such entity is {@link Serializable} and also can be
 * activated or deactivated in the persistence storage. In order to guarantee consistency a field
 * stores the optimistic locking of the entity.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@MappedSuperclass
public abstract class Model implements Serializable {

    @Version
    @Column(name = "version", nullable = false)
    private int version;

    public abstract UUID getId();

    public abstract void setId(UUID id);

    public int getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Model other = (Model) obj;
        return Objects.equals(getId(), other.getId());
    }

    protected Date newDate(Date time) {
        checkNotNull(time, "Date should not be [null].");
        return newDate(time.getTime());
    }

    protected Date newDate(long time) {
        return new Date(time);
    }

    protected static Date newDate(int year, int month, int dayOfMonth) {
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, dayOfMonth);
        return calendar.getTime();
    }
}
