// Copyright 2014 University of Detroit Mercy.

package com.udm.identitymanager.domain.service.identity;

/**
 * Specifies the behavior of the encryption service.
 *
 * @author Oscar Rico (martinezr.oscar@gmail.com)
 */
public interface EncryptionService {
    
    /**
     * Encrypts a given string.
     * 
     * @param aPlainTextValue the plain text string that will be encrypted.
     * @return the encrypted value as a string.
     */
    String encrypt(String aPlainTextValue);
}
