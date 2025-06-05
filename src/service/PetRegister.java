package service;

import model.Pet;
import model.PetAddress;
import repository.FormRepository;
import repository.PetFile;
import util.PetValidator;

import java.util.Scanner;

public class PetRegister {
    public static void register() {
        Scanner scanner = new Scanner(System.in);
        Pet pet = new Pet();
        PetAddress petAddress = new PetAddress();

        FormRepository.readFileLine(1);
        pet.setName(PetValidator.validatePetName(scanner));

        FormRepository.readFileLine(2);
        pet.setSpecie(PetValidator.validatePetSpecie(scanner));

        FormRepository.readFileLine(3);
        pet.setGender(PetValidator.validatePetGender(scanner));

        FormRepository.readFileLine(4);
        System.out.print("Rua: ");
        petAddress.setStreet(scanner.nextLine());
        System.out.print("Número: ");
        petAddress.setNumber(PetValidator.validateAddressNumber(scanner.nextLine()));
        System.out.print("Cidade: ");
        petAddress.setCity(scanner.nextLine());
        pet.setAddress(petAddress);

        FormRepository.readFileLine(5);
        pet.setAge(PetValidator.validatePetAge(scanner));

        FormRepository.readFileLine(6);
        pet.setWeight(PetValidator.validatePetWeight(scanner));

        FormRepository.readFileLine(7);
        pet.setBreed(PetValidator.validatePetBreed(scanner));

        PetFile.save(pet);
    }
}
