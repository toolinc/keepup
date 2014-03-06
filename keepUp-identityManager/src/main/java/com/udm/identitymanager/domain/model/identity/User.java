// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.model.identity;

import com.udm.common.domain.model.DomainObjectConcurrencySafe;
import com.udm.identitymanager.domain.DomainRegistry;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Represents a user.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Entity
@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(name = "userName_uk", columnNames = {"userName"}),
        @UniqueConstraint(name = "personUser_uk", columnNames = {"idPerson"})})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class User extends DomainObjectConcurrencySafe {

    @NotNull
    @Id
    @Column(name = "idUser")
    private UUID id;

    @Column(name = "userName", length = 255, nullable = false)
    private String userName;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @NotNull
    @Embedded
    private Enablement enablement;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        assertArgumentNotNull(id, "The id cannot be null.");
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        assertArgumentNotEmpty(userName, "The provided user name is null or empty.");
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        assertArgumentNotEmpty(password, "The provided password is null or empty.");
        this.password = password;
    }

    public Enablement getEnablement() {
        return enablement;
    }

    public void setEnablement(Enablement enablement) {
        assertArgumentNotNull(enablement, "The provide enablement is null.");
        this.enablement = enablement;
    }

    public void changePassword(String aCurrentPassword, String aChangedPassword) {
        assertArgumentNotEmpty(aCurrentPassword, "Current password must be provided.");
        assertArgumentEquals(getPassword(), asEncryptedValue(aCurrentPassword),
                "Current password not confirmed.");
        protectPassword(aCurrentPassword, aChangedPassword);
    }

    private String asEncryptedValue(String aPlainTextPassword) {
        return DomainRegistry.encryptionService().encrypt(aPlainTextPassword);
    }

    private void protectPassword(String aCurrentPassword, String aChangedPassword) {
        assertPasswordsNotSame(aCurrentPassword, aChangedPassword);
        assertPasswordNotWeak(aChangedPassword);
        assertUsernamePasswordNotSame(aChangedPassword);
        setPassword(this.asEncryptedValue(aChangedPassword));
    }

    private void assertPasswordsNotSame(String aCurrentPassword, String aChangedPassword) {
        assertArgumentNotEquals(aCurrentPassword, aChangedPassword, "The password is unchanged.");
    }

    private void assertPasswordNotWeak(String aPlainTextPassword) {
        this.assertArgumentFalse(DomainRegistry.passwordService().isWeak(aPlainTextPassword),
                "The password must be stronger.");
    }

    private void assertUsernamePasswordNotSame(String aPlainTextPassword) {
        this.assertArgumentNotEquals(getUserName(), aPlainTextPassword,
                "The username and password must not be the same.");
    }
}
