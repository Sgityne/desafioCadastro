package cli;

import model.Pet;
import repository.PetFile;
import service.*;
import util.PetValidator;
import util.Validators;

import java.util.ArrayList;
import java.util.List;
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
    private final static String EDIT_MENU = """
            Escolha o campo que deseja alterar:
            1 - Nome ou sobrenome
            2 - Idade
            3 - Peso
            4 - Raça
            5 - Endereço
            6 - Salvar Alterações""";

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
                        editMenu();
                        continue;
                    case 3:
                        deleteMenu();
                        continue;
                    case 4:
                        PetSearch.listAllPets();
                        continue;
                    case 5:
                        printPets(searchMenu());
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

    public static List<Pet> searchMenu() {
        Scanner scanner = new Scanner(System.in);
        int[] criteria = new int[2];
        int c = 0;
        System.out.println("Escolha a espécie que deseja procurar:");
        int petSpecie = PetValidator.validatePetSpecie(scanner);
        do {
            System.out.println("Escolha o critério de busca: ");
            System.out.print(SEARCH_MENU);
            criteria[c] = Validators.menuNumberFilter(scanner, 8);
            if (criteria[1] != 0) {
                break;
            }
            String choice = " ";
            while (!(choice.charAt(0) == 'n' || choice.charAt(0) == 's')) {
                System.out.print("Deseja escolher um 2º critérios (Sim ou Não)?\n>> ");
                choice = scanner.nextLine().toLowerCase();
            }
            if (choice.charAt(0) == 'n') {
                break;
            }
            c++;
        } while (true);
        List<Pet> petList = new ArrayList<>(PetSearchFilter.Filter(petSpecie, criteria));
        if (petList.isEmpty()) {
            System.out.println("Nenhum pet encontrado.");
            return List.of();
        }
        return petList;
    }

    public static void editMenu() {
        Scanner scanner = new Scanner(System.in);
        List<Pet> petList = searchMenu();
        if (petList.isEmpty()) {
            return;
        }
        System.out.println("Digite o número do pet que deseja alterar:");
        printPets(petList);
        int chosenPet = Validators.menuNumberFilter(scanner, petList.size());
        PetEditor.editPet(petList.remove(chosenPet - 1));
    }

    public static void deleteMenu() {
        Scanner scanner = new Scanner(System.in);
        List<Pet> petList = searchMenu();
        if (petList.isEmpty()) {
            return;
        }
        System.out.println("Digite o número do pet que deseja deletar:");
        printPets(petList);
        int chosenPet = Validators.menuNumberFilter(scanner, petList.size());
        PetDelete.delete(petList.remove(chosenPet - 1));
    }

    public static int editCriteriaMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(EDIT_MENU);
        return Validators.menuNumberFilter(scanner, 6);
    }

    public static void printPets(List<Pet> pets) {
        int c = 1;
        for (Pet pet : pets) {
            System.out.println(c + ". " + pet.print());
            c++;
        }
    }
}
