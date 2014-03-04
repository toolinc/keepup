// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.model.access;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.model.DomainObjectBuilder;
import com.udm.common.domain.model.DomainObjectConcurrencySafe;
import com.udm.identitymanager.domain.model.identity.User;

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
 * Represents a user in a particular group.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@Table(name = "GroupMember", uniqueConstraints = {
        @UniqueConstraint(name = "groupMember_uk", columnNames = {"idGroup", "idUser"})})
public class GroupMember extends DomainObjectConcurrencySafe {

    @Id
    @Column(name = "idGroupMember")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGroup", referencedColumnName = "idGroup",
            foreignKey = @ForeignKey(name = "group_groupMember_fk"))
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser",
            foreignKey = @ForeignKey(name = "user_groupMember_fk"))
    private User user;

    @Deprecated
    public GroupMember() {
    }

    private GroupMember(Builder builder) {
        setId(builder.id);
        setGroup(builder.group);
        setUser(builder.user);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        assertArgumentNotNull(user, "The user cannot be null.");
        this.user = user;
    }

    /**
     * Builder of {@link com.udm.identitymanager.domain.model.access.GroupMember} instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder extends AssertionConcern implements
            DomainObjectBuilder<GroupMember> {

        private UUID id;
        private Group group;
        private User user;

        private Builder() {
        }

        public Builder setGroup(Group group) {
            assertArgumentNotNull(group, "The group cannot be null.");
            this.group = group;
            return this;
        }

        public Builder setUser(User user) {
            assertArgumentNotNull(user, "The user cannot be null.");
            this.user = user;
            return this;
        }

        /**
         * Creates a instances of
         * {@link com.udm.identitymanager.domain.model.access.GroupMember} given the
         * specified characteristics on the
         * {@link com.udm.identitymanager.domain.model.access.GroupMember.Builder}.
         *
         * @return a new instance
         *         {@link com.udm.identitymanager.domain.model.access.GroupMember}
         */
        @Override
        public GroupMember build() {
            id = UUID.randomUUID();
            return new GroupMember(this);
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         *         {@link com.udm.identitymanager.domain.model.access.GroupMember.Builder}
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
