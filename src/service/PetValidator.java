package service;

import java.util.Scanner;

public class PetValidator {

    private static final String NAO_INFORMADO = "não informado";

    public static String validatePetName(Scanner scanner) {
        System.out.print(">> ");
        String name = scanner.nextLine();
        if (name == null || name.trim().isEmpty()) {
            return NAO_INFORMADO;
        }

        try {
            if (!name.contains(" ")) {
                System.out.println("O pet deverá ter nome e sobrenome.");
                throw new IllegalArgumentException();
            }

            if (!name.matches("([A-z ]+)")) {
                System.out.println("O pet deverá conter SOMENTE letras.");
                throw new IllegalArgumentException();
            }

            return name.trim();
        } catch (IllegalArgumentException e) {
            return validatePetName(scanner);
        }
    }

    public static String validatePetWeight(Scanner scanner) {
        System.out.print(">> ");
        String weight = scanner.nextLine();
        if (weight == null || weight.trim().isEmpty()) {
            return NAO_INFORMADO;
        }

        try {
            if (weight.contains(",")) {
                weight = weight.replace(",", ".");
            }

            double weightD = Double.parseDouble(weight);
            if (weightD < 0.5 || weightD > 60) {
                throw new IllegalArgumentException();
            }

            return String.valueOf(weight);
        } catch (IllegalArgumentException e) {
            System.out.println("Peso inválido. Digite outro número.");
            return validatePetWeight(scanner);
        }
    }

    /**
     * @param age years followed by months
     * @return {@link model.Pet#age age}
     */
    public static String validatePetAge(Scanner scanner) {
        System.out.print(">> ");
        String age = scanner.nextLine();
        if (age == null || age.trim().isEmpty()) {
            return NAO_INFORMADO;
        }
        try {
            if (age.contains(",")) {
                age = age.replace(",", ".");
            }

            double ageD = Double.parseDouble(age);
            if (ageD > 20 || ageD <= 0) {
                throw new IllegalArgumentException();
            }
            return String.valueOf(age);
        } catch (IllegalArgumentException e) {
            System.out.println("Idade inválida. Digite outro número.");
            return validatePetAge(scanner);
        }
    }

    public static String validatePetBreed(Scanner scanner) {
        System.out.print(">> ");
        String breed = scanner.nextLine();
        if (breed == null || breed.trim().isEmpty()) {
            return NAO_INFORMADO;
        }

        try {
            if (!breed.matches("([A-z ]+)")) {
                throw new IllegalArgumentException();
            }
            return breed;
        } catch (IllegalArgumentException e) {
            System.out.println("Raça inválida.");
            return validatePetBreed(scanner);
        }
    }

    public static String validateAddressNumber(String number) {
        if (number == null || number.trim().isEmpty() || number.matches("([^1-9])")) {
            return NAO_INFORMADO;
        }
        return number;
    }

    public static int validatePetSpecie(Scanner scanner) {
        System.out.print("1 - Cachorro\n2 - Gato\n>> ");
        try {
            int num = Integer.parseInt(scanner.nextLine());
            if (num == 1 || num == 2) {
                return num;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Espécie inválida");
            return validatePetSpecie(scanner);
        }
    }

    public static char validatePetGender(Scanner scanner) {
        System.out.print("F - Fêmea\nM - Macho\n>> ");
        String input = scanner.nextLine().toUpperCase();
        char gender = input.charAt(0);
        try {
            if (gender == 'F' || gender == 'M') {
                return gender;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Gênero inválido");
            return validatePetGender(scanner);
        }
    }
}
