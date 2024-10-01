package forCommands.Commands;

import forCommands.Command;
import lombok.AllArgsConstructor;

import java.util.Deque;

@AllArgsConstructor
public class History implements Command {
    private final Deque<String> commandHistory;

    @Override
    public void execute(String argument) {
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
