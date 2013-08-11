/*
 * @(#)PasswordUtil.java
 *
 * Copyright (c) 2009 Moduad, Inc.
 * All rights reserved.
 */
package com.khan.opensns.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * Class description here.
 *
 * @author Sehwan Noh (sehwan@java2go.net)
 * @version 1.0
 */
public class PasswordUtil {

    public static byte[] encodePassword(String password, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(password.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String encodePasswordToHex(String password, String algorithm) {
        return new String(Hex.encodeHex(encodePassword(password, algorithm)));
    }

    public static String encodePasswordToBase64(String password, String algorithm) {
        return new String(Base64.encodeBase64(encodePassword(password, algorithm)));
    }

    public static void main(String[] args) {
        System.out.println(encodePasswordToHex("pwdpwd123", "SHA-256"));
        System.out.println(encodePasswordToBase64("pwdpwd123", "SHA-256"));
        System.out.println(encodePasswordToBase64("password", "SHA-256"));
    }

}
