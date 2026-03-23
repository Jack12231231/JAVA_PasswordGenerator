package com.example.password;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            logger.info("Приложение Генератор паролей запущено");

            System.out.print("Введите длину пароля: ");
            int length = scanner.nextInt();
            if (length <= 0) {
                throw new IllegalArgumentException("Длина пароля должна быть > 0");
            }
            scanner.nextLine(); // очистка буфера после nextInt()

            System.out.print("Использовать строчные буквы? (y/да): ");
            String ans = scanner.nextLine().trim().toLowerCase();
            boolean useLower = ans.equals("y") || ans.equals("да") || ans.equals("yes");

            System.out.print("Использовать заглавные буквы? (y/да): ");
            ans = scanner.nextLine().trim().toLowerCase();
            boolean useUpper = ans.equals("y") || ans.equals("да") || ans.equals("yes");

            System.out.print("Использовать цифры? (y/да): ");
            ans = scanner.nextLine().trim().toLowerCase();
            boolean useDigits = ans.equals("y") || ans.equals("да") || ans.equals("yes");

            System.out.print("Использовать спецсимволы? (y/да): ");
            ans = scanner.nextLine().trim().toLowerCase();
            boolean useSpecial = ans.equals("y") || ans.equals("да") || ans.equals("yes");

            if (!useLower && !useUpper && !useDigits && !useSpecial) {
                throw new IllegalArgumentException("Должен быть выбран хотя бы один тип символов");
            }

            // Логирование параметров
            logger.info("Параметры ввода: длина=" + length +
                    ", строчные=" + useLower +
                    ", заглавные=" + useUpper +
                    ", цифры=" + useDigits +
                    ", спецсимволы=" + useSpecial);

            Generator generator = new Generator();
            Password pw = generator.generate(length, useLower, useUpper, useDigits, useSpecial);

            System.out.println("Сгенерированный пароль: " + pw.getValue());

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            logger.error("Исключение: " + e.toString());
        } finally {
            logger.info("Программа завершена");
            scanner.close();
        }
    }
}