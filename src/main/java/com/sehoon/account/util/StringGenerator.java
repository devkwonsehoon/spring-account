package com.sehoon.account.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class StringGenerator {
    public static String generateAccountNumber() {
        String pattern = "MMddHHmmss"; // 편의를 위해 시간 기준 생성
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, new Locale("KOREAN", "KOREA"));
        return formatter.format(new Date());
    }

    public static String generateTransactionNumber() {
        Random random = new Random();
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 15;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
