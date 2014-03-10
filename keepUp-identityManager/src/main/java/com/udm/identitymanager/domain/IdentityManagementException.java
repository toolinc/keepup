// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain;

import com.udm.common.AssertionConcern;

import java.util.ResourceBundle;

/**
 * Thrown by the identity manager when a problem occurs.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class IdentityManagementException extends Exception {

    /**
     * Constructs a new IdentityManagementException exception with the specified detail message.
     *
     * @param message the detail message
     */
    public IdentityManagementException(String message) {
        super(message);
    }

    /**
     * Builder of {@link IdentityManagementException}
     * instances.
     *
     * @author Oscar Rico (martinezr.oscar@gmail.com)
     */
    public static class Builder extends AssertionConcern {

        private static final String PATH = "IdentityManagementMessages";
        private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(PATH);
        private String message;

        private Builder() {
        }

        public Builder setMessage(String message) {
            assertArgumentNotNull(message, "Message cannot be null.");
            message = BUNDLE.getString(message);
            return this;
        }

        public Builder setMessage(String message, Object... params) {
            assertArgumentNotNull(message, "Message cannot be null.");
            assertArgumentNotNull(params, "Parameters cannot be null.");
            message = String.format(BUNDLE.getString(message), params);
            return this;
        }

        /**
         * Creates a instances of
         * {@link IdentityManagementException} given the
         * specified characteristics on the
         * {@link IdentityManagementException.Builder}.
         *
         * @return a new instance
         *         {@link IdentityManagementException}
         */
        public IdentityManagementException build() {
            return new IdentityManagementException(message);
        }

        /**
         * Provides a new builder.
         *
         * @return a new instance of
         * {@link IdentityManagementException.Builder}
         */
        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
