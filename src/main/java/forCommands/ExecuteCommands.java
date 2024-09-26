package forCommands;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

public class ExecuteCommands {
    private final CommandManager commands;
    private final Deque<String> commandHistory;
    public ExecuteCommands(CommandManager commandManager) {
        this.commands = commandManager;
        this.commandHistory = new LinkedList<>();
    }
    public boolean executeCommand(String inputLine) {
        String[] parts = inputLine.trim().split(" ", 2);
        String commandName = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1].trim() : "";

        Command command = commands.getCommand(commandName);
        if (command == null) {
            System.out.println("Команда не найдена: " + commandName);
            return false;
        }

        // Если команда требует аргумент
        if (commandRequiresArgument(commandName)) {
            // Если аргументы переданы, команда выполняется с ними
            if (!argument.isEmpty()) {
                command.execute(argument);
            } else {
                // Если аргументы не переданы, команда должна запросить их интерактивно
                command.execute("");  // Переходим к интерактивному вводу внутри команды
            }
        } else {
            // Если команда не требует аргументов
            if (!argument.isEmpty()) {
                System.out.println("Команда " + commandName + " не принимает аргументов");
                return false;
            }
            // Выполняем команду без аргументов
            command.execute("");
        }
        recordCommand(commandName); // Записываем команду в историю
        return false;
    }

    // Метод для проверки наличия аргументов у команд
    private boolean commandRequiresArgument(String commandName) {
        Set<String> commandsWithArgs = Set.of("add", "updateid", "removebyid", "executescript", "addifmin", "removegreater");
        return commandsWithArgs.contains(commandName);  // Если команда есть в этом списке, она требует аргумент
    }
    //метод для записи команд в историю
    private void recordCommand(String command) {
        int maxHistorySize = 7;
        if (commandHistory.size() >= maxHistorySize) {
            commandHistory.pollFirst();
        }
        commandHistory.addLast(command);
    }
}