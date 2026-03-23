package com.example.password;

import org.apache.log4j.Logger;
import java.util.Random;

public class Generator {
    static Logger logger = Logger.getLogger(Generator.class);

    public Password generate(int length, boolean useLower, boolean useUpper, boolean useDigits, boolean useSpecial) {
        logger.info("Начало генерации пароля длиной " + length);

        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+";

        Random rand = new Random();
        StringBuilder pool = new StringBuilder();
        StringBuilder mandatory = new StringBuilder();

        if (useLower) { pool.append(lower); mandatory.append(lower.charAt(rand.nextInt(lower.length()))); logger.info("Включены строчные буквы"); }
        if (useUpper) { pool.append(upper); mandatory.append(upper.charAt(rand.nextInt(upper.length()))); logger.info("Включены заглавные буквы"); }
        if (useDigits) { pool.append(digits); mandatory.append(digits.charAt(rand.nextInt(digits.length()))); logger.info("Включены цифры"); }
        if (useSpecial) { pool.append(special); mandatory.append(special.charAt(rand.nextInt(special.length()))); logger.info("Включены спецсимволы"); }

        if (pool.length() == 0) throw new IllegalArgumentException("Не выбран ни один тип символов");
        if (length < mandatory.length()) throw new IllegalArgumentException("Длина пароля должна быть не меньше количества выбранных типов (" + mandatory.length() + ")");

        StringBuilder sb = new StringBuilder(mandatory);
        String poolStr = pool.toString();
        for (int i = mandatory.length(); i < length; i++) {
            sb.append(poolStr.charAt(rand.nextInt(poolStr.length())));
        }

        char[] chars = sb.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = rand.nextInt(chars.length);
            char temp = chars[i]; chars[i] = chars[j]; chars[j] = temp;
        }

        String password = new String(chars);
        logger.info("Пароль успешно сгенерирован: " + password);
        return new Password(password);
    }
}
