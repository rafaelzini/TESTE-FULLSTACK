package com.zini.locadora.utils;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.UUID;

public class Utils {

    public static LocalDateTime nowTime() {
        ZoneId brt = ZoneId.of("America/Sao_Paulo");
        return LocalDateTime.now(brt);
    }

    public static LocalDate now() {
        ZoneId brt = ZoneId.of("America/Sao_Paulo");
        return LocalDate.now(brt);
    }

    public static String getRandomToken() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomDigits(int digits) {
        StringBuilder text = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < digits; i++) {
            text.append(random.nextInt(10));
        }
        return text.toString();
    }

    public static byte[] fileToBytes(File file) throws IOException {
        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        try {
            byte[] buffer = new byte[4096];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream(file);
            int read = 0;
            while ((read = ios.read(buffer)) != -1) {
                ous.write(buffer, 0, read);
            }
        } finally {
            try {
                if (ous != null) ous.close();
            } catch (IOException e) {
            }

            try {
                if (ios != null) ios.close();
            } catch (IOException e) {
            }
        }
        return ous.toByteArray();
    }

}
