package forCOmmands;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console {
    private final Scanner scanner;
    private final CommandManager commands;

    public Console(CommandManager commands, Scanner scanner) {
        this.commands = commands;
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("Здраствуйте. Программа готова к работе");
        String inputLine;
        boolean needExit = false;
        while (!needExit) {
            System.out.println("Введите комманду");
            try {
                inputLine = scanner.nextLine();
            }catch(NoSuchElementException e){
                System.out.println("Произошла ошибка при чтении команды: " + e.getMessage());
                return;
            }
            needExit = commands.executeCommand(inputLine);
        }
    }
}

