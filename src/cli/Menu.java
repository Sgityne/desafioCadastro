package cli;

import service.PetRegister;
import service.PetSearch;
import util.PetValidator;
import util.Validators;

import java.util.Scanner;

public class Menu {
    private final static String MENU_OPTIONS = """
            
            1 - Cadastrar um novo pet
            2 - Alterar os dados do pet cadastrado
            3 - Deletar um pet cadastrado
            4 - Listar todos os pets cadastrados
            5 - Listar pets por algum critério (idade, nome, raça)
            6 - Sair\n""" +
            ">> ";

    private final static String SEARCH_MENU = """
            1 - Nome ou sobrenome
            2 - Sexo
            3 - Idade
            4 - Peso
            5 - Raça
            6 - Endereço
            7 - Data específica
            8 - Intervalo de datas
            """;

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(MENU_OPTIONS);
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
                        searchMenu();
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

    public static void searchMenu() {
        Scanner scanner = new Scanner(System.in);
        int[] criteria = new int[2];
        int c = 0;
        System.out.println("Escolha a espécie que deseja procurar:");
        int petSpecie = PetValidator.validatePetSpecie(scanner);
        do {
            System.out.println("Escolha o critério de busca: ");
            System.out.print(SEARCH_MENU);
            criteria[c] = Validators.menuNumberFilter(scanner);
            if (criteria[1] != 0) {
                break;
            }
            String choice = " ";
            while (!(choice.charAt(0) == 'n' || choice.charAt(0) == 's')) {
                System.out.print("Deseja escolher um 2º critérios (Sim ou Não)?\n>> ");
                choice = scanner.next().toLowerCase();
            }
            if (choice.charAt(0) == 'n') {
                break;
            }
            c++;
        } while (true);
        PetSearch.listPetsByCriteria(petSpecie, criteria);
    }
}
