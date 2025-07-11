package service;

import cli.Menu;
import model.Pet;
import repository.FormRepository;
import repository.PetFile;
import util.PetValidator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class PetEditor {
    public static void editPet(Pet pet) {
        Scanner scanner = new Scanner(System.in);
        pet.setName(pet.getName().replaceAll("\u001B\\[1m", "").replaceAll("\u001B\\[0m", ""));
        while (true) {
            int option = Menu.editCriteriaMenu();
            switch (option) {
                case 1:
                    FormRepository.readFileLine(1);
                    pet.setName(PetValidator.validatePetName(scanner));
                    continue;
                case 2:
                    FormRepository.readFileLine(5);
                    pet.setAge(PetValidator.validatePetAge(scanner));
                    continue;
                case 3:
                    FormRepository.readFileLine(6);
                    pet.setWeight(PetValidator.validatePetWeight(scanner));
                    continue;
                case 4:
                    FormRepository.readFileLine(7);
                    pet.setBreed(PetValidator.validatePetBreed(scanner));
                    continue;
                case 5:
                    FormRepository.readFileLine(4);
                    System.out.print("Rua: ");
                    pet.getAddress().setStreet(scanner.nextLine());
                    System.out.print("Número: ");
                    pet.getAddress().setNumber(PetValidator.validateAddressNumber(scanner.nextLine()));
                    System.out.print("Cidade: ");
                    pet.getAddress().setCity(scanner.nextLine());
                    continue;
                case 6:
                    try {
                        Path filePath = FileFinder.parseFileName(PetFile.listAllFiles(), pet.getName());
                        PetFile.save(pet, filePath);
                        return;
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
            }
        }
    }
}
