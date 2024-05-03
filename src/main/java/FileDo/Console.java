package FileDo;


import java.util.Scanner;

public class Console {
    private Scanner scanner;
    private Comands comands;

    public Console(Comands comands, Scanner scanner) {
        this.comands = comands;
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("Здраствуйте. Программа готова к работе");
        String inputLine;
        boolean needExit = false;
        while (!needExit) {
            System.out.println("Введите комманду");
            inputLine = scanner.nextLine();
            needExit = comands.executeCommand(inputLine);
        }
    }
}

