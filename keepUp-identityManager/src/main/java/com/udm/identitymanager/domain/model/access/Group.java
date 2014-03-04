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
 * Represents a group.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@Table(name = "Groups", uniqueConstraints = {
        @UniqueConstraint(name = "group_uk", columnNames = {"name"})})
public class Group extends DomainObjectConcurrencySafe {

    @Id
    @Column(name = "idGroup")
    private UUID id;

    @Column(name = "name", length = 150)
    private String name;

    @Deprecated
    public Group() {
    }

    private Group(Builder builder) {
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
     * Builder of {@link com.udm.identitymanager.domain.model.access.Group} instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder extends AssertionConcern implements
            DomainObjectBuilder<Group> {

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
         * {@link com.udm.identitymanager.domain.model.access.Group} given the
         * specified characteristics on the
         * {@link com.udm.identitymanager.domain.model.access.Group.Builder}.
         *
         * @return a new instance
         *         {@link com.udm.identitymanager.domain.model.access.Group}
         */
        @Override
        public Group build() {
            id = UUID.randomUUID();
            return new Group(this);
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         *         {@link com.udm.identitymanager.domain.model.access.Group.Builder}
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
