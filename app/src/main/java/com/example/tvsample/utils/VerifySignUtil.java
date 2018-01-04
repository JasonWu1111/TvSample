package com.example.tvsample.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by JasonWu on 29/12/2017
 */

public class VerifySignUtil {
    private static final String[] a = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static final String KEY = "key=BWzDWUx4dafDQnKt";

    private static String a(byte paramByte) {
        int i = paramByte;
        if (paramByte < 0) {
            i = paramByte + 256;
        }
        paramByte = (byte) (i / 16);
        return a[paramByte] + a[(i % 16)];
    }

    public static String getSignature(String queryString) {
        byte[] paramArrayOfByte = getMessageDigest().digest((queryString + KEY).getBytes());
        StringBuilder localStringBuffer = new StringBuilder();
        int i = 0;
        while (i < paramArrayOfByte.length) {
            localStringBuffer.append(a(paramArrayOfByte[i]));
            i += 1;
        }
        return localStringBuffer.toString();
    }

    public static boolean checkSignValid(String src, String signature) {
        String encodeStr = getSignature(src);
        return encodeStr.equals(signature);
    }

    private static MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String src = "identifier=fbaccount1&credential=78945621234&telephone=13660123456&sourceType=1";
        System.out.println(getSignature(src));
    }

}
