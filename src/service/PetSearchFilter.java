package service;

import model.Pet;
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
            System.out.print("Digite o nome ou sobrenome que deseja buscar:\n>> ");
            petList = SearchFilters.filterByName(scanner, petList);
        }
        if (criteria[0] == 2 || criteria[1] == 2) {
            System.out.println("Escolhe o sexo que deseja buscar");
            petList = SearchFilters.filterByGender(scanner, petList);
        }
        if (criteria[0] == 3 || criteria[1] == 3) {
            System.out.print("Digite a idade que deseja buscar:\n>> ");
            petList = SearchFilters.filterByAge(scanner, petList);
        }
        if (criteria[0] == 4 || criteria[1] == 4) {
            System.out.print("Digite o peso que deseja buscar:\n>> ");
            petList = SearchFilters.filterByWeight(scanner, petList);
        }
        if (criteria[0] == 5 || criteria[1] == 5) {
            System.out.print("Digite a raça que deseja buscar:\n>> ");
            petList = SearchFilters.filterByBreed(scanner, petList);
        }
        if (criteria[0] == 6 || criteria[1] == 6) {
            System.out.print("Digite o endereço que deseja buscar:\n>> ");
            petList = SearchFilters.filterByAddress(scanner, petList);
        }
        if (criteria[0] == 7 || criteria[1] == 7) {
            int[][] date = new int[2][2];
            System.out.println("Digite a data (mês/ano):");
            date[0] = Validators.isAValidDate(scanner);
            date[1] = date[0];
            return SearchFilters.filterByData(date, petList);
        }
        if (criteria[0] == 8 || criteria[1] == 8) {
            int[][] dates = new int[2][2];
            System.out.println("Digite a data inicial (mês/ano):");
            dates[0] = Validators.isAValidDate(scanner);
            System.out.println("Digite a data final (mês/ano):");
            dates[1] = Validators.isAValidDate(scanner);
            return SearchFilters.filterByData(dates, petList);
        }

        return petList;
    }
}
