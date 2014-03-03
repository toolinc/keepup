// Copyright 2014 University of Detroit Mercy.

package com.udm.health.domain.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

/**
 * Represents a USA state using the United States Postal Service Code (ISO 3166).
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public enum State implements Serializable {

    AL("Alabama"),
    AK("Alaska"),
    AZ("Arizona"),
    AR("Arkansas"),
    CA("California"),
    CO("Colorado"),
    CT("Connecticut"),
    DE("Delaware"),
    DC("District of Columbia"),
    FL("Florida"),
    GA("Georgia"),
    HI("Hawaii"),
    ID("Idaho"),
    IL("Illinois"),
    IN("Indiana"),
    IA("Iowa"),
    KS("Kansas"),
    KY("Kentucky"),
    LA("Louisiana"),
    ME("Maine"),
    MD("Maryland"),
    MA("Massachusetts"),
    MI("Michigan"),
    MN("Minnesota"),
    MS("Mississippi"),
    MO("Missouri"),
    MT("Montana"),
    NE("Nebraska"),
    NV("Nevada"),
    NH("New Hampshire"),
    NJ("New Jersey"),
    NM("New Mexico"),
    NY("New York"),
    NC("North Carolina"),
    ND("North Dakota"),
    OH("Ohio"),
    OK("Oklahoma"),
    OR("Oregon"),
    PA("Pennsylvania"),
    RI("Rhode Island"),
    SC("South Carolina"),
    SD("South Dakota"),
    TN("Tennessee"),
    TX("Texas"),
    UT("Utah"),
    VT("Vermont"),
    VA("Virginia"),
    WA("Washington"),
    WV("West Virginia"),
    WI("Wisconsin"),
    WY("Wyoming");

    private final String stateName;

    private State(String stateName) {
        this.stateName = checkNotNull(stateName).toUpperCase();
    }

    public String getStateName() {
        return stateName;
    }
}
