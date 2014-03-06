// Copyright 2014 University of Detroit Mercy.

package com.udm.common;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Date;
import java.util.GregorianCalendar;

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
        checkState(anObject1.equals(anObject2), aMessage);
    }

    protected void assertArgumentNotEquals(Object anObject1, Object anObject2, String aMessage) {
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

    protected Date newDate(Date time) {
        checkNotNull(time, "Date should not be [null].");
        return newDate(time.getTime());
    }

    protected Date newDate(long time) {
        return new Date(time);
    }

    protected static Date newDate(int year, int month, int dayOfMonth) {
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, dayOfMonth);
        return calendar.getTime();
    }
}
