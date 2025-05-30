package service;

import model.Pet;
import repository.PetFile;

import java.io.IOException;

public class PetSearch {
    public static void listAllPets() {
        try {
            Pet[] pets = PetFile.listAllPets();
            for (int i = 0; i < pets.length; i++) {
                System.out.println(i + 1 + ". " + pets[i].print());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
