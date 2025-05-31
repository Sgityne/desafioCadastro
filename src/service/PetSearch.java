package service;

import model.Pet;
import model.PetAddress;
import repository.PetFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PetSearch {
    public static void listAllPets() {
        int c = 1;
        try {
            File[] files = PetFile.listAllFiles();
            for (File file : files) {
                List<String> lines = PetFile.readFileLines(file);
                Pet pet = parsePetFromLines(lines);
                System.out.println(c + ". " + pet.print());
                c++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
}
