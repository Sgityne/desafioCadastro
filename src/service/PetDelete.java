package service;

import model.Pet;
import repository.PetFile;

import java.util.Scanner;

public class PetDelete {
    public static void delete(Pet pet) {
        Scanner scanner = new Scanner(System.in);
        pet.setName(pet.getName().replaceAll("\u001B\\[1m", "").replaceAll("\u001B\\[0m", ""));
        while (true) {
            System.out.print("Tem certeza que deseja deletar o arquivo? (SIM ou NÃO)\n" +
                    ">> ");
            String choice = scanner.nextLine();
            if (choice.toLowerCase().charAt(0) == 's') {
                boolean deleted = PetFile.delete(pet);
                if (deleted) {
                    System.out.println("Pet deletado com sucesso");
                }
                return;
            } else if (choice.toLowerCase().charAt(0) == 'n') {
                return;
            } else {
                System.out.println("Valor inválido.");
            }
        }
    }
}
