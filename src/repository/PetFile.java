package repository;

import model.Pet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetFile {
    final static Path PETFOLDERPATH = Paths.get("data\\petsCadastrados");
    static Path folderPath = PETFOLDERPATH.toAbsolutePath();

    public static void save(Pet pet) {
        if (Files.notExists(folderPath)) {
            try {
                Files.createDirectory(folderPath);
            } catch (IOException e) {
                System.out.println("Erro ao criar o diretório");
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dateTime = LocalDateTime.now().format(formatter);
        Path filePath = Paths.get(folderPath.toString(),
                dateTime + "-" + pet.getName().replaceAll("\\s","").toUpperCase() + ".txt");
        if (Files.notExists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.out.println("Erro ao tentar criar o cadastro do pet");
            }
        }
        try (FileWriter fw = new FileWriter(filePath.toString());
             BufferedWriter writer = new BufferedWriter(fw)) {
            writer.write("1 - " + pet.getName() + "\n" +
                    "2 - " + pet.getSpecie() + "\n" +
                    "3 - " + pet.getGender() + "\n" +
                    "4 - " + pet.getAddress().getAddress() + "\n" +
                    "5 - " + pet.getAge() + " anos\n" +
                    "6 - " + pet.getWeight() + "kg\n" +
                    "7 - " + pet.getBreed());
            writer.flush();
        } catch (IOException e) {
            System.out.println("Erro ao tentar registrar o pet");
        }
    }
}
