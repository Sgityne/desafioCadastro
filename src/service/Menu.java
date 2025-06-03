package service;

import java.util.Scanner;

public class Menu {
    final static String MENUOPTIONS = """
            
            1 - Cadastrar um novo pet
            2 - Alterar os dados do pet cadastrado
            3 - Deletar um pet cadastrado
            4 - Listar todos os pets cadastrados
            5 - Listar pets por algum critério (idade, nome, raça)
            6 - Sair\n""" +
            ">> ";

    final static String SEARCHMENU = """
            1 - Nome ou sobrenome
            2 - Sexo
            3 - Idade
            4 - Peso
            5 - Raça
            6 - Endereço\n""" +
            ">> ";

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(MENUOPTIONS);
            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        PetRegister.register();
                        continue;
                    case 2:

                    case 3:

                    case 4:
                        PetSearch.listAllPets();
                        continue;
                    case 5:
                        PetSearch.listPetsByCriteria();
                        continue;
                    case 6:
                        scanner.close();
                        break;
                    default:
                        System.out.println("Número inválido. Digite outro número.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
            }
        }
    }

    public static int numberFiler(Scanner scanner) {
        while (true) {
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
