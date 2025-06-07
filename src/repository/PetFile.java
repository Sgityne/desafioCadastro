package repository;

import model.Pet;
import service.FileFinder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.Constants.FORMATTER;
import static util.Constants.PET_FOLDER_PATH;

public class PetFile {
    private static final Path FOLDER_PATH = Paths.get(PET_FOLDER_PATH).toAbsolutePath();

    public static Path createFile(Pet pet) {
        if (Files.notExists(FOLDER_PATH)) {
            try {
                Files.createDirectory(FOLDER_PATH);
            } catch (IOException e) {
                System.out.println("Erro ao criar o diretório");
            }
        }
        String dateTime = LocalDateTime.now().format(FORMATTER);
        return  Paths.get(FOLDER_PATH.toString(),
                dateTime + "-" + pet.getName().replaceAll("\\s", "").toUpperCase() + ".txt");
    }

    public static void save(Pet pet, Path filePath) {
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
        if (Files.notExists(FOLDER_PATH)) {
            throw new IOException("Diretório não encontrado");
        }
        try (Stream<Path> paths = Files.list(FOLDER_PATH)) {
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

    public static boolean delete(Pet pet) {
        try {
            Path filePath = FileFinder.parseFileName(listAllFiles(), pet.getName());
            Files.delete(filePath);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
