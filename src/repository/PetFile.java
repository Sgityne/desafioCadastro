package repository;

import model.Pet;
import model.PetAddress;

import java.io.*;
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

    public static Pet[] listAllPets() throws IOException {
        if (Files.notExists(folderPath)) {
            throw new IOException("Diretório não encontrado");
        }
        File[] files = folderPath.toFile().listFiles();
        if (files == null) {
            throw new IOException("Nenhum pet cadastrado");
        }
        Pet[] petList = new Pet[files.length];
        for (int i = 0; i < files.length; i++) {
            Pet pet = new Pet();
            PetAddress petAddress = new PetAddress();
            try (FileReader fr = new FileReader(files[i]);
            BufferedReader reader = new BufferedReader(fr)) {
                pet.setName(reader.readLine().split(" - ")[1]);
                pet.setSpecie(reader.readLine().split(" - ")[1].toUpperCase());
                pet.setGender(reader.readLine().split(" - ")[1].replace("ê", "e").toUpperCase());
                String address = reader.readLine();
                pet.setAge(reader.readLine().split(" - ")[1].split(" anos")[0]);
                pet.setWeight(reader.readLine().split(" - ")[1].split("kg")[0]);
                pet.setBreed(reader.readLine().split(" - ")[1]);

                String[] addresses = address.split(" - ");
                petAddress.setStreet(addresses[1].split(", ")[0]);
                petAddress.setNumber(addresses[1].split(", ")[1]);
                petAddress.setCity(addresses[2]);
                pet.setAddress(petAddress);

                petList[i] = pet;
            }
        }
        return petList;
    }
}
