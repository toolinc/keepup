// Copyright 2014 University of Detroit Mercy.

package com.udm.common.domain.model;

import static com.google.common.base.Preconditions.checkNotNull;

import com.udm.common.AssertionConcern;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.MappedSuperclass;

/**
 * Represents an identified domain object which is {@link java.io.Serializable}.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@MappedSuperclass
public abstract class DomainObject extends AssertionConcern implements Serializable {

    private static final long serialVersionUID = 1L;

    protected DomainObject() {
    }

    public abstract UUID getId();

    public abstract void setId(UUID id);

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !Objects.equals(getClass(), obj.getClass())) {
            return false;
        }
        final DomainObject other = (DomainObject) obj;
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
