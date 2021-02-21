package org.db.REST.DB.services;

import java.util.Base64;

public class EncryptionBase64 {

    public static String encode(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes());
    }
}
