import repository.FormRepository;
import service.MainMenu;

public class Main {
    public static void main(String[] args) {
        FormRepository.createFile();
        FormRepository.readFile();
        MainMenu.mainMenu();
    }
}
