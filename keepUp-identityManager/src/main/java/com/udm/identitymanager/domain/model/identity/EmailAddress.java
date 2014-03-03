// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.model.identity;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.validation.Email;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Represents an email address.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Embeddable
public class EmailAddress extends AssertionConcern implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String REGEX = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    @Email
    @Column(name = "emailAddress", nullable = false)
    private String address;

    @Deprecated
    public EmailAddress() {
        super();
    }

    public EmailAddress(String anAddress) {
        this.setAddress(anAddress);
    }

    public String getAddress() {
        return address;
    }

    private void setAddress(String anAddress) {
        this.assertArgumentNotEmpty(anAddress, "The email address is required.");
        this.assertArgumentLength(anAddress, 1, 100,
                "The email address must be 100 characters or less.");
        this.assertArgumentTrue(Pattern.matches(REGEX, anAddress),
                "The email address format is invalid.");
        this.address = anAddress;
    }

    @Override
    public boolean equals(Object anObject) {
        if (anObject == null || this.getClass() != anObject.getClass()) {
            return false;
        }
        EmailAddress typedObject = (EmailAddress) anObject;
        return Objects.equals(getAddress(), typedObject.getAddress());
    }

    @Override
    public int hashCode() {
        return (17861 * 179) + Objects.hashCode(getAddress());
    }

    @Override
    public String toString() {
        return "EmailAddress [address=" + address + "]";
    }
}
