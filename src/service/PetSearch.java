package service;

import model.Pet;
import model.PetAddress;
import repository.PetFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetSearch {
    static List<Pet> getPetFiles(){
        try {
            File[] files = PetFile.listAllFiles();
            List<Pet> pets = new ArrayList<>();
            for (File file : files) {
                List<String> lines = PetFile.readFileLines(file);
                Pet pet = parsePetFromLines(lines);
                pets.add(pet);
            }
            return pets;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    public static void listAllPets() {
        List<Pet> pets = getPetFiles();
        printPets(pets);
    }

    private static void printPets (List<Pet> pets) {
        int c = 1;
        for (Pet pet : pets) {
            System.out.println(c + ". " + pet.print());
            c++;
        }
    }

    private static Pet parsePetFromLines(List<String> lines) {
        Pet pet = new Pet();
        PetAddress petAddress = new PetAddress();

        pet.setName(lines.get(0).split(" - ")[1]);
        pet.setSpecie(lines.get(1).split(" - ")[1].toUpperCase());
        pet.setGender(lines.get(2).split(" - ")[1].replace("ê", "e").toUpperCase());
        String address = lines.get(3);
        pet.setAge(lines.get(4).split(" - ")[1].split(" anos")[0]);
        pet.setWeight(lines.get(5).split(" - ")[1].split("kg")[0]);
        pet.setBreed(lines.get(6).split(" - ")[1]);

        String[] addresses = address.split(" - ");
        petAddress.setStreet(addresses[1].split(", ")[0]);
        petAddress.setNumber(addresses[1].split(", ")[1]);
        petAddress.setCity(addresses[2]);
        pet.setAddress(petAddress);

        return pet;
    }

    public static void listPetsByCriteria() {
        Scanner scanner = new Scanner(System.in);
        int[] criteria = new int[2];
        int c = 0;
        System.out.println("Escolha a espécie que deseja procurar:");
        int petSpecie = PetValidator.validatePetSpecie(scanner);
        do {
            System.out.println("Escolha o critério de busca: ");
            System.out.print(Menu.SEARCHMENU);
            criteria[c] = Menu.numberFiler(scanner);
            if (criteria[1] != 0) {
                break;
            }
            System.out.print("Deseja escolher um 2º critérios (Sim ou Não)?\n>> ");
            String choice = scanner.next().toLowerCase();
            if (choice.charAt(0) == 'n') {
                break;
            }
            c++;
        } while (true);
        List<Pet> petFilteredList = PetSearchFilter.Filter(petSpecie, criteria);
        printPets(petFilteredList);
    }
}
