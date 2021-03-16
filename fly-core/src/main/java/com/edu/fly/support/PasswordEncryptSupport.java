package com.edu.fly.support;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class PasswordEncryptSupport implements PasswordEncoder {

    /** The hash code. */
    private static String hashCode = "SHA-256";

    /**
     * Encrypt by SHA 2.
     *
     * @param strSrc
     *            the str src
     * @return the string
     */
    public static String encryptBySha2(String strSrc) {
        return encrypt(strSrc, hashCode);
    }

    /**
     * Encrypt.
     *
     * @param strSrc
     *            the str src
     * @param encName
     *            the enc name
     * @return the string
     */
    // MD5 length=32
    public static String encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            if (StringUtils.isEmpty(encName)) {
                encName = hashCode;
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * Bytes 2 hex.
     *
     * @param bts
     *            the bts
     * @return the string
     */
    private static String bytes2Hex(byte[] bts) {
        StringBuilder description = new StringBuilder();

        String tmp;
        for (int i = 0; i < bts.length; i++) {
            tmp = Integer.toHexString(bts[i] & 0xFF);
            if (tmp.length() == 1) {
                description.append("0");
            }
            description.append(tmp);
        }

        return description.toString();
    }

    @Override
    public String encode(CharSequence charSequence) {
        return encrypt(charSequence.toString(), hashCode);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return StringUtils.equals(encode(rawPassword), encodedPassword);
    }

}
