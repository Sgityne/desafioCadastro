package repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static util.Constants.FORM_PATH;

public class FormRepository {
    private static final Path FILE_PATH = Paths.get(FORM_PATH).toAbsolutePath();
    final static String FORM_TEXT = """
            1 - Qual o nome e sobrenome do pet?
            2 - Qual o tipo do pet (Cachorro/Gato)?
            3 - Qual o sexo do animal?
            4 - Qual endereço e bairro que ele foi encontrado?
            5 - Qual a idade aproximada do pet?
            6 - Qual o peso aproximado do pet?
            7 - Qual a raça do pet?""";

    public static void createFile() {
        if (Files.notExists(FILE_PATH)) {
            try {
                Files.createFile(FILE_PATH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (FileWriter fw = new FileWriter(FILE_PATH.toString());
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(FORM_TEXT);
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile() {
        try (FileReader fr = new FileReader(FILE_PATH.toFile());
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
        try (FileReader fr = new FileReader(FILE_PATH.toFile());
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
