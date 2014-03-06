// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.model.access;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.model.DomainObjectBuilder;
import com.udm.common.domain.model.DomainObjectConcurrencySafe;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Represents a role.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@Table(name = "Role", uniqueConstraints = {
        @UniqueConstraint(name = "role_uk", columnNames = {"name"})})
public class Role extends DomainObjectConcurrencySafe {

    @Id
    @Column(name = "idRole")
    private UUID id;

    @Column(name = "name", length = 150)
    private String name;

    @Deprecated
    public Role() {
    }

    private Role(Builder builder) {
        setId(builder.id);
        setName(builder.name);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        assertArgumentNotNull(id, "The id cannot be null.");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        assertArgumentNotEmpty(name, "The name cannot be empty or null.");
        this.name = name.toUpperCase();
    }

    /**
     * Builder of {@link com.udm.identitymanager.domain.model.access.Role} instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder extends AssertionConcern implements
            DomainObjectBuilder<Role> {

        private UUID id;
        private String name;

        private Builder() {
        }

        public Builder setName(String name) {
            assertArgumentNotEmpty(name, "The name cannot be empty or null.");
            this.name = name.toUpperCase();
            return this;
        }

        /**
         * Creates a instances of
         * {@link com.udm.identitymanager.domain.model.access.Role} given the
         * specified characteristics on the
         * {@link com.udm.identitymanager.domain.model.access.Role.Builder}.
         *
         * @return a new instance
         *         {@link com.udm.identitymanager.domain.model.access.Role}
         */
        @Override
        public Role build() {
            id = UUID.randomUUID();
            return new Role(this);
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         *         {@link com.udm.identitymanager.domain.model.access.Role.Builder}
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
