// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.model.identity;

import com.udm.common.AssertionConcern;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * Represents the contact information of a person.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Embeddable
public class ContactInformation extends AssertionConcern implements Serializable {

    private static final long serialVersionUID = 1L;

    @Embedded
    private EmailAddress emailAddress;

    @Embedded
    private TelephoneNumber telephoneNumber;

    @Deprecated
    public ContactInformation() {
    }

    public ContactInformation(EmailAddress emailAddress, TelephoneNumber telephoneNumber) {
        setEmailAddress(emailAddress);
        setTelephoneNumber(telephoneNumber);
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        assertArgumentNotNull(emailAddress, "Email address cannot be empty or null.");
        this.emailAddress = emailAddress;
    }

    public TelephoneNumber getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(TelephoneNumber telephoneNumber) {
        assertArgumentNotNull(telephoneNumber, "TelephoneNumber cannot be empty or null.");
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public boolean equals(Object anObject) {
        if (anObject == null || this.getClass() != anObject.getClass()) {
            return false;
        }
        ContactInformation typedObject = (ContactInformation) anObject;
        return Objects.equals(getEmailAddress(), typedObject.getEmailAddress()) &&
                Objects.equals(getTelephoneNumber(), typedObject.getTelephoneNumber());
    }

    @Override
    public int hashCode() {
        return (71 * 179) + Objects.hashCode(getEmailAddress())
                + Objects.hashCode(getTelephoneNumber());
    }
}
