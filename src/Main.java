import repository.FormRepository;
import cli.Menu;

public class Main {
    public static void main(String[] args) {
        FormRepository.createFile();
        FormRepository.readFile();
        Menu.mainMenu();
    }
}
