// Copyright 2014 University of Detroit Mercy.

package com.udm.common.domain.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Represents a domain object that guarantees consistency through the use of optimistic locking.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@MappedSuperclass
public abstract class DomainObjectConcurrencySafe extends DomainObject {

    private static final long serialVersionUID = 1L;

    protected DomainObjectConcurrencySafe() {
    }

    @Version
    @Column(name = "version", nullable = false)
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int aVersion) {
        failWhenConcurrencyViolation(aVersion);
        version = aVersion;
    }

    protected void failWhenConcurrencyViolation(int aVersion) {
        assertArgumentEquals(version, aVersion,
                "Stale data detected. Entity was already modified.");
    }
}
