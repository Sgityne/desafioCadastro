package repository;

import model.Pet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PetFile {
    final static Path PETFOLDERPATH = Paths.get("data\\petsCadastrados");
    static Path folderPath = PETFOLDERPATH.toAbsolutePath();
    public final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");

    public static void save(Pet pet) {
        if (Files.notExists(folderPath)) {
            try {
                Files.createDirectory(folderPath);
            } catch (IOException e) {
                System.out.println("Erro ao criar o diretório");
            }
        }
        String dateTime = LocalDateTime.now().format(FORMATTER);
        Path filePath = Paths.get(folderPath.toString(),
                dateTime + "-" + pet.getName().replaceAll("\\s", "").toUpperCase() + ".txt");
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

    public static List<Path> listAllFiles() throws IOException {
        if (Files.notExists(folderPath)) {
            throw new IOException("Diretório não encontrado");
        }
        try (Stream<Path> paths = Files.list(folderPath)) {
            List<Path> filePaths = paths
                    .filter(Files::isRegularFile)
                    .toList();

            if (filePaths.isEmpty()) {
                throw new IOException("Nenhum pet cadastrado");
            }
            return filePaths;
        }
    }

    public static List<String> readFileLines(Path path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            return reader.lines().collect(Collectors.toList());
        }
    }
}
