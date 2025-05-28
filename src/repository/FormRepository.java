package repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FormRepository {
    final static Path FORMPATH = Paths.get("data\\formulario.txt");
    static Path filePath = FORMPATH.toAbsolutePath();
    final static String FORMTEXT = """
            1 - Qual o nome e sobrenome do pet?
            2 - Qual o tipo do pet (Cachorro/Gato)?
            3 - Qual o sexo do animal?
            4 - Qual endereço e bairro que ele foi encontrado?
            5 - Qual a idade aproximada do pet?
            6 - Qual o peso aproximado do pet?
            7 - Qual a raça do pet?""";

    public static void createFile() {
        if (Files.notExists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (FileWriter fw = new FileWriter(filePath.toString());
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(FORMTEXT);
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile() {
        try (FileReader fr = new FileReader(filePath.toFile());
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFileLine(int line) {
        try (FileReader fr = new FileReader(filePath.toFile());
             BufferedReader br = new BufferedReader(fr)) {
            String lines;
            while((lines = br.readLine()) != null) {
                if (Integer.parseInt(String.valueOf(lines.charAt(0))) == line) {
                    System.out.println(lines);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
