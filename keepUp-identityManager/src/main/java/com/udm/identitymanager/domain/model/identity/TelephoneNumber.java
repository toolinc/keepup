// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.model.identity;

import com.udm.common.AssertionConcern;
import com.udm.common.domain.validation.Telephone;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Represents a telephone number.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
@Embeddable
public class TelephoneNumber extends AssertionConcern implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String REGEX = "\\d{5,20}";

    @Telephone
    @Column(name = "telephone", length = 20)
    private String number;

    @Deprecated
    public TelephoneNumber() {
    }

    public TelephoneNumber(String aNumber) {
        this.setNumber(aNumber);
    }

    public String getNumber() {
        return this.number;
    }

    private void setNumber(String aNumber) {
        this.assertArgumentNotEmpty(aNumber, "TelephoneNumber number is required.");
        this.assertArgumentLength(aNumber, 5, 20,
                "TelephoneNumber number may not be more than 20 characters.");
        this.assertArgumentTrue(Pattern.matches(REGEX, aNumber),
                "TelephoneNumber number or its format is invalid.");
        this.number = aNumber;
    }

    @Override
    public boolean equals(Object anObject) {
        if (anObject == null || getClass() != anObject.getClass()) {
            return false;
        }
        TelephoneNumber typedObject = (TelephoneNumber) anObject;
        return Objects.equals(getNumber(), typedObject.getNumber());
    }

    @Override
    public int hashCode() {
        int hashCodeValue = (35137 * 239) + Objects.hashCode(getNumber());
        return hashCodeValue;
    }

    @Override
    public String toString() {
        return "TelephoneNumber [number=" + number + "]";
    }
}
