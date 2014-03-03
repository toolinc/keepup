// Copyright 2014 University of Detroit Mercy.

package com.udm.common;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Provides different assertions.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class AssertionConcern {

    protected AssertionConcern() {
        super();
    }

    protected void assertArgumentEquals(Object anObject1, Object anObject2, String aMessage) {
        checkState(!anObject1.equals(anObject2), aMessage);
    }

    protected void assertArgumentTrue(boolean aBoolean, String aMessage) {
        checkState(aBoolean, aMessage);
    }

    protected void assertArgumentFalse(boolean aBoolean, String aMessage) {
        checkState(!aBoolean, aMessage);
    }

    protected void assertArgumentNotNull(Object anObject, String aMessage) {
        checkNotNull(anObject, aMessage);
    }

    protected void assertArgumentNotEmpty(String aString, String aMessage) {
        checkState(!isNullOrEmpty(aString), aMessage);
    }

    protected void assertArgumentLength(String aString, int aMinimum, int aMaximum,
                                        String aMessage) {
        int length = aString.trim().length();
        if (length < aMinimum || length > aMaximum) {
            throw new IllegalArgumentException(aMessage);
        }
    }
}
