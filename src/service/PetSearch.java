package service;

import model.Pet;
import repository.PetFile;

import java.io.IOException;

public class PetSearch {
    public static void listAllPets() {
        try {
            Pet[] pets = PetFile.listAllPets();
            for (int i = 0; i < pets.length; i++) {
                System.out.println(i + 1 + ". " + pets[i].getName()
                        + " - " + pets[i].getSpecie()
                        + " - " + pets[i].getGender()
                        + " - " + pets[i].getAddress().getAddress()
                        + " - " + pets[i].getAge() + " anos"
                        + " - " + pets[i].getWeight() + "kg"
                        + " - " + pets[i].getBreed());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
