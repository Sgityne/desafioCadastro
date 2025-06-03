package service;

import model.Pet;
import model.PetGender;
import model.PetSpecie;
import util.Validators;

import java.util.List;
import java.util.Scanner;

public class PetSearchFilter {
    public static List<Pet> Filter(int num, int[] criteria) {
        Scanner scanner = new Scanner(System.in);
        List<Pet> petList;
        PetSpecie cSpecie;
        if (num == 1) {
            cSpecie = PetSpecie.CACHORRO;
        } else {
            cSpecie = PetSpecie.GATO;
        }
        PetSpecie chosenSpecie = cSpecie;
        petList = PetSearch.getPetFiles().stream()
                .filter(pet -> pet.getSpecieType() == chosenSpecie)
                .toList();

        if (criteria[0] == 1 || criteria[1] == 1) {
            System.out.print("Digite o nome ou sobrenome que deseja buscar:\n>>> ");
            petList = filterByName(scanner, petList);
        }
        if (criteria[0] == 2 || criteria[1] == 2) {
            System.out.println("Escolhe o sexo que deseja buscar");
            petList = filterByGender(scanner, petList);
        }
        if (criteria[0] == 3 || criteria[1] == 3) {
            System.out.print("Digite a idade que deseja buscar:\n>>> ");
            petList = filterByAge(scanner, petList);
        }
        if (criteria[0] == 4 || criteria[1] == 4) {
            System.out.print("Digite o peso que deseja buscar:\n>>> ");
            petList = filterByWeight(scanner, petList);
        }
        if (criteria[0] == 5 || criteria[1] == 5) {
            System.out.print("Digite a raça que deseja buscar:\n>>> ");
            petList = filterByBreed(scanner, petList);
        }
        if (criteria[0] == 6 || criteria[1] == 6) {
            System.out.print("Digite o endereço que deseja buscar:\n>>> ");
            petList = filterByAddress(scanner, petList);
        }
        return petList;
    }

    private static List<Pet> filterByName(Scanner scanner, List<Pet> petList) {
        String name = Validators.isNotBlank(scanner).toLowerCase();
        return petList.stream()
                .filter(pet -> pet.getName().toLowerCase().contains(name))
                .toList();
    }

    private static List<Pet> filterByGender(Scanner scanner, List<Pet> petList) {
        char input = PetValidator.validatePetGender(scanner);
        PetGender gender;
        if (input == 'F') {
            gender = PetGender.FEMEA;
        } else {
            gender = PetGender.MACHO;
        }
        PetGender finalGender = gender;
        return petList.stream()
                .filter(pet -> pet.getGenderType() == finalGender)
                .toList();
    }

    private static List<Pet> filterByAge(Scanner scanner, List<Pet> petList) {
        String age = Validators.isAValidNumber(scanner);
        return petList.stream()
                .filter(pet -> pet.getAge().contains(age))
                .toList();
    }

    private static List<Pet> filterByWeight(Scanner scanner, List<Pet> petList) {
        String weight = Validators.isAValidNumber(scanner);
        return petList.stream()
                .filter(pet -> {
                    try {
                        return Double.parseDouble(pet.getWeight()) == Double.parseDouble(weight);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .toList();
    }

    private static List<Pet> filterByBreed(Scanner scanner, List<Pet> petList) {
        String breed = Validators.isNotBlank(scanner).toLowerCase();
        return petList.stream()
                .filter(pet -> pet.getBreed().toLowerCase().contains(breed))
                .toList();
    }

    private static List<Pet> filterByAddress(Scanner scanner, List<Pet> petList) {
        String address = Validators.isNotBlank(scanner).toLowerCase();
        return petList.stream()
                .filter(pet -> pet.getAddress().getStreet().toLowerCase().contains(address) ||
                        pet.getAddress().getCity().toLowerCase().contains(address))
                .toList();
    }
}
