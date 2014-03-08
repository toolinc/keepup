// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.model.identity;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.model.DomainObjectBuilder;
import com.udm.common.domain.model.DomainObjectConcurrencySafe;
import com.udm.identitymanager.domain.validation.PersonName;

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
 * Represents a system entity.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@Table(name = "System")
public class System extends DomainObjectConcurrencySafe {

    @NotNull
    @Id
    @Column(name = "idSystem")
    private UUID id;

    @PersonName
    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @NotNull
    @OneToOne(cascade = {PERSIST, MERGE}, fetch = LAZY, optional = false)
    @JoinColumn(name = "idPerson", referencedColumnName = "idPerson",
            foreignKey = @ForeignKey(name = "system_person_fk"))
    private Person administrator;

    @Deprecated
    public System() {
    }

    private System(Builder builder) {
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        assertArgumentNotNull(id, "Id of the system cannot be null.");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        assertArgumentNotNull(name, "Name of the system cannot be null.");
        this.name = name.toUpperCase();
    }

    public Person getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Person administrator) {
        assertArgumentNotNull(administrator, "Administrator of the system cannot be null.");
        this.administrator = administrator;
    }

    /**
     * Builder of {@link com.udm.identitymanager.domain.model.identity.System} instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder extends AssertionConcern implements
            DomainObjectBuilder<System> {

        private UUID id;
        private String name;
        private Person administrator;

        private Builder() {
        }

        public Builder setName(String name) {
            assertArgumentNotNull(name, "Name of the system cannot be null.");
            this.name = name.toUpperCase();
            return this;
        }

        public Builder setAdministrator(Person administrator) {
            assertArgumentNotNull(administrator, "Administrator of the system cannot be null.");
            this.administrator = administrator;
            return this;
        }

        /**
         * Creates a instances of {@link com.udm.identitymanager.domain.model.identity.System}
         * given the specified characteristics on the
         * {@link com.udm.identitymanager.domain.model.identity.System.Builder}.
         *
         * @return a new instance {@link com.udm.identitymanager.domain.model.identity.System}
         */
        @Override
        public System build() {
            id = UUID.randomUUID();
            return new System(this);
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         * {@link com.udm.identitymanager.domain.model.identity.System.Builder}
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
