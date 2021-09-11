package com.demon.mall.Util;

import com.demon.mall.Common.Encryption;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String getMD5(String s) throws NoSuchAlgorithmException {
        MessageDigest md=MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md.digest((s+ Encryption.Encryption_code).getBytes()));
    }

    public static void main(String[] args) {
        String md5 = null;
        try {
            md5 = getMD5("1059478058");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(md5);

    }
}
