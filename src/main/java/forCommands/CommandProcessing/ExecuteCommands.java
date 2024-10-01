package forCommands.CommandProcessing;

import forCommands.Command;

import java.util.Set;

public class ExecuteCommands {
    private final CommandManager commandManager;

    public ExecuteCommands(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    // метод для выполнения команд (+ обработка аргументов)
    public boolean executeCommand(String inputLine) {
        String[] parts = inputLine.trim().split(" ", 2);

        /*
        Разбиваем строку inputLine на две части:
        parts[0] — название команды, parts[1] - аргумент команды (если есть)
        trim ()- удаление пробелов в начале и в конце строки
        split(" ", 2) - строка разбивается на не более чем 2 части
         */

        String commandName = parts[0].toLowerCase();

        /*
        Присваиваем переменной commandName название команды в нижнем регистре
        при помощи toLowerCase(). Пример: Add, add, ADD обрабатываются одинаково).
         */

        String argument = parts.length > 1 ? parts[1].trim() : "";

        /*
        1) Проверяем, переданы ли аргументы (то есть, длина массива parts больше 1)
        2) Если аргументы есть, присваиваем их переменной argument. Если аргументов нет, присваиваем пустую строку ("").
         */

        Command command = commandManager.getCommand(commandName);
        if (command == null) {
            System.out.println("Команда не найдена: " + commandName);
            return false;
        }

        // Проверка наличия аргументов
        boolean requiresArgument = commandRequiresArgument(commandName);

        // Если команда НЕ требует аргументов, но аргумент передан — это ошибка
        if (!requiresArgument && !argument.isEmpty()) {
            System.out.println("Команда " + commandName + " не принимает аргументов");
            return false;
        }

        // Выполнение команды с аргументом или без
        command.execute(requiresArgument ? argument : "");

        // Запись команды в историю
        commandManager.recordCommand(commandName);
        return false;
    }

    // Метод для проверки наличия аргументов у команд
    private boolean commandRequiresArgument(String commandName) {
        Set<String> commandsWithArgs = Set.of("add", "updateid", "removebyid", "executescript", "addifmin", "removegreater");
        return commandsWithArgs.contains(commandName);  // Если команда есть в этом списке, она требует аргумент
    }
}