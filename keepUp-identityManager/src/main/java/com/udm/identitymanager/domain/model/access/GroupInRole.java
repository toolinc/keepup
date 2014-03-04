// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.model.access;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.model.DomainObjectBuilder;
import com.udm.common.domain.model.DomainObjectConcurrencySafe;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Represents a group in a particular role.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@Table(name = "GroupInRole", uniqueConstraints = {
        @UniqueConstraint(name = "groupInRole_uk", columnNames = {"idGroup", "idRole"})})
public class GroupInRole extends DomainObjectConcurrencySafe {

    @Id
    @Column(name = "idGroupInRole")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGroup", referencedColumnName = "idGroup",
            foreignKey = @ForeignKey(name = "group_groupInRole_fk"))
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRole", referencedColumnName = "idRole",
            foreignKey = @ForeignKey(name = "role_GroupInRole_fk"))
    private Role role;

    @Deprecated
    public GroupInRole() {
    }

    private GroupInRole(Builder builder) {
        setId(builder.id);
        setGroup(builder.group);
        setRole(builder.role);
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        assertArgumentNotNull(group, "The group cannot be null.");
        this.group = group;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        assertArgumentNotNull(role, "The role cannot be null.");
        this.role = role;
    }

    /**
     * Builder of {@link com.udm.identitymanager.domain.model.access.GroupInRole} instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder extends AssertionConcern implements
            DomainObjectBuilder<GroupInRole> {

        private UUID id;
        private Group group;
        private Role role;

        private Builder() {
        }

        public Builder setGroup(Group group) {
            assertArgumentNotNull(group, "The group cannot be null.");
            this.group = group;
            return this;
        }

        public Builder setRole(Role role) {
            assertArgumentNotNull(role, "The role cannot be null.");
            this.role = role;
            return this;
        }

        /**
         * Creates a instances of
         * {@link com.udm.identitymanager.domain.model.access.GroupInRole} given the
         * specified characteristics on the
         * {@link com.udm.identitymanager.domain.model.access.GroupInRole.Builder}.
         *
         * @return a new instance
         *         {@link com.udm.identitymanager.domain.model.access.GroupInRole}
         */
        @Override
        public GroupInRole build() {
            id = UUID.randomUUID();
            return new GroupInRole(this);
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         *         {@link com.udm.identitymanager.domain.model.access.GroupInRole.Builder}
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
