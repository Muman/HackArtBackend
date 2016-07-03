package com.hack.art.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by ROLO on 03.04.2016.
 */
@Component
public class PasswordGenerator {

    private static final String NUMBERS = "0123456789";
    private static final String UPPER_ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
    private static final String SPECIALCHARACTERS = "@#$%&*";
    private static final int MINLENGTHOFPASSWORD = 8;

    public String password;

    @Autowired
    private LogUtil logger;

    public PasswordGenerator() {
        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        while (sb.length() < MINLENGTHOFPASSWORD) {
            int randomInt = random.nextInt(UPPER_ALPHABETS.length());

            if (randomInt < NUMBERS.length() && randomInt > SPECIALCHARACTERS.length()) {
                sb.append(NUMBERS.charAt(randomInt));
            } else if (randomInt > NUMBERS.length()) {
                if ((randomInt % 2) == 0) {
                    sb.append(UPPER_ALPHABETS.charAt(randomInt));
                } else {
                    sb.append(LOWER_ALPHABETS.charAt(randomInt));
                }
            } else if (randomInt < NUMBERS.length() && randomInt < SPECIALCHARACTERS.length()) {
                sb.append(SPECIALCHARACTERS.charAt(randomInt));
            }
        }

        this.password = sb.toString();
    }

    public String generateMD5Hash(){
        String md5 = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            md5 = new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }
}
