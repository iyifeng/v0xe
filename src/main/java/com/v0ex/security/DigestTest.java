package com.v0ex.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zbj on 16/1/25.
 */
public class DigestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DigestTest.class);

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        /**
         * SHA-1 安全散列算法
         */
        String algName = args.length >= 2 ? args[1] : "SHA-1";
        MessageDigest alg = MessageDigest.getInstance(algName);
        byte[] input = Files.readAllBytes(Paths.get(args[0]));
        byte[] hash = alg.digest(input);
        String d = "";
        for (int i = 0; i < hash.length; i++) {
            int v = hash[i] & 0xFF;
            if (v < 16) d += "0";
            d += Integer.toString(v,16).toUpperCase() + "";
        }
        LOGGER.info(d);
    }
}
