package service;

import java.util.Scanner;

public class MainMenu {
    final static String MENUOPTIONS = """
            1 - Cadastrar um novo pet
            2 - Alterar os dados do pet cadastrado
            3 - Deletar um pet cadastrado
            4 - Listar todos os pets cadastrados
            5 - Listar pets por algum critério (idade, nome, raça)
            6 - Sair""";

    public static void mainMenu() {
        System.out.println(MENUOPTIONS);
        Scanner scanner = new Scanner(System.in);
        int option = numberFiler(scanner);
        while (option != 6) {
            if (option == 1) {
                PetRegister.register();
            } else if (option == 2) {

            } else if (option == 3) {

            } else if (option == 4) {

            } else {

            }
            System.out.println(MENUOPTIONS);
            option = numberFiler(scanner);
        }
    }

    private static int numberFiler(Scanner scanner) {
        while (true) {
            System.out.print(">>> ");
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                if (num > 0 && num <= 6) {
                    return num;
                } else {
                    System.out.println("Número inválido. Digite outro número.");
                }
            } else {
                System.out.println("Entrada inválida. Digite apenas números.");
                scanner.nextLine();
            }
        }
    }
}
