// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.infrastructure.service;

import com.udm.common.AssertionConcern;
import com.udm.identitymanager.domain.service.identity.EncryptionService;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Encryption service using MD5 algorithm.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public class MD5EncryptionService extends AssertionConcern implements EncryptionService {

    private static final String ALGORITHM = "MD5";
    private static final String ENCODING = "UTF-8";

    @Override
    public String encrypt(String aPlainTextValue) {
        this.assertArgumentNotEmpty(aPlainTextValue,
                "The plain text value to encrypt must be provided.");
        String encryptedValue = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(aPlainTextValue.getBytes(ENCODING));
            BigInteger bigInt = new BigInteger(1, messageDigest.digest());
            encryptedValue = bigInt.toString(16);
        } catch (Exception e) {
            throw new IllegalStateException("Encryption process failed.", e);
        }
        return encryptedValue;
    }
}
