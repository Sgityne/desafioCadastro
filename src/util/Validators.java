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
}
