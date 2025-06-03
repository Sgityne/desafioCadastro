package service;

import model.Pet;
import model.PetGender;
import repository.PetFile;
import util.Validators;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchFilters {
    public static List<Pet> filterByName(Scanner scanner, List<Pet> petList) {
        String name = Validators.isNotBlank(scanner).toLowerCase();
        return petList.stream()
                .filter(pet -> pet.getName().toLowerCase().contains(name))
                .toList();
    }

    public static List<Pet> filterByGender(Scanner scanner, List<Pet> petList) {
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

    public static List<Pet> filterByAge(Scanner scanner, List<Pet> petList) {
        String age = Validators.isAValidNumber(scanner);
        return petList.stream()
                .filter(pet -> pet.getAge().contains(age))
                .toList();
    }

    public static List<Pet> filterByWeight(Scanner scanner, List<Pet> petList) {
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

    public static List<Pet> filterByBreed(Scanner scanner, List<Pet> petList) {
        String breed = Validators.isNotBlank(scanner).toLowerCase();
        return petList.stream()
                .filter(pet -> pet.getBreed().toLowerCase().contains(breed))
                .toList();
    }

    public static List<Pet> filterByAddress(Scanner scanner, List<Pet> petList) {
        String address = Validators.isNotBlank(scanner).toLowerCase();
        return petList.stream()
                .filter(pet -> pet.getAddress().getStreet().toLowerCase().contains(address) ||
                        pet.getAddress().getCity().toLowerCase().contains(address))
                .toList();
    }

    public static List<Pet> filterByData(int[][] dates, List<Pet> petList) {
        List<Pet> filteredList = new ArrayList<>();
        LocalDate initialDate = LocalDate.of(dates[0][1], dates[0][0], 1);
        int finalDay = YearMonth.of(dates[1][1], dates[1][0]).lengthOfMonth();
        LocalDate finalDate = LocalDate.of(dates[1][1], dates[1][0], finalDay);
        try {
            List<Path> paths = PetFile.listAllFiles();
            for (Path path : paths) {
                String[] fileNameParts = path.getFileName().toString().split("-");
                LocalDate fileDate = LocalDate.parse(fileNameParts[0], PetFile.FORMATTER);
                if (!fileDate.isBefore(initialDate) && !fileDate.isAfter(finalDate)) {
                    String rawName = fileNameParts[1].split("\\W")[0];
                    for (Pet pet : petList) {
                        String normalizedPetName = pet.getName().replaceAll("\\s", "").toUpperCase();
                        if (normalizedPetName.equals(rawName)) {
                            filteredList.add(pet);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return filteredList;
    }
}
