package com.sehoon.account.util;

import java.util.Random;

public class StringGenerator {
    public static String generateAccountNumber() {
        String pattern = "0123456789";
        int length = 10;
        return getString(pattern, length);
    }

    public static String generateTransactionNumber() {
        String pattern = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 15;
        return getString(pattern, length);
    }

    private static String getString(String pattern, int length) {
        Random random = new Random();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(pattern.length());
            char randomChar = pattern.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
