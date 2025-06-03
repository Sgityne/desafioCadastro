package util;

import java.util.Scanner;

public class Validators {
    public static String isNotBlank(Scanner scanner) {
        try {
            String string = scanner.nextLine().toLowerCase();
            if (string.isBlank()) {
                throw new IllegalArgumentException();
            }
            return string;
        } catch (IllegalArgumentException e) {
            System.out.println("Nada foi digitado! Tente novamente.\n>>> ");
            return isNotBlank(scanner);
        }
    }

    public static String isAValidNumber(Scanner scanner) {
        try {
            int num = Integer.parseInt(scanner.nextLine());
            if (num > 0) {
                return String.valueOf(num);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Número inválido.");
            return isAValidNumber(scanner);
        }
    }

    public static int[] isAValidDate(Scanner scanner) {
        try {
            String date = isNotBlank(scanner).trim();
            if (!date.contains("/")) {
                throw new IllegalArgumentException("Deve conter '/' entre o mês e o ano");
            }
            if (date.length() > 7) {
                throw new IllegalArgumentException("Data Inválida");
            }

            int month = Integer.parseInt(date.split("/")[0]);
            if (month > 12 || month < 0) {
                throw new IllegalArgumentException("Mês Inválido");
            }

            int year = Integer.parseInt(date.split("/")[1]);
            if (year < 0) {
                throw new IllegalArgumentException("Ano Inválido");
            }
            return new int[] {month, year};

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isAValidDate(scanner);
        }
    }
}
