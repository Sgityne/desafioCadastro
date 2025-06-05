package util;

import java.time.format.DateTimeFormatter;

public class Constants {
    public final static String FORM_PATH = "data\\formulario.txt";
    public final static String PET_FOLDER_PATH = "data\\petsCadastrados";
    public static final String NAO_INFORMADO = "não informado";
    public final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
    public final static String BOLD_START = "\033[1m";
    public final static String BOLD_END = "\033[0m";
}
