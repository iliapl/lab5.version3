package forCOmmands.Commands;

import forCOmmands.Command;

import java.util.Deque;

public class History implements Command {
    private final Deque<String> commandHistory;

    public History(Deque<String> commandHistory) {
        this.commandHistory = commandHistory;
    }

    @Override
    public void execute(String argument){
        if (commandHistory.isEmpty()) {
            System.out.println("История команд пуста.");
            return;
        }
        System.out.println("Последние 7 команд:");
        int index = 1;
        for (String command : commandHistory) {
            System.out.println(index + ". " + command);
            index++;
        }
    }
}
