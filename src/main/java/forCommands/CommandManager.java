package forCommands;

import ReadFromConsole.ConsoleReader;
import forCommands.Commands.*;
import forVehicles.Vehicle;
import forVehicles.VehiclesCollecton;
import java.util.*;

public class CommandManager {
    private final Map<String, Command> commands;
    private final Set<Vehicle> vehicles;
    private final Deque<String> commandHistory;

    public CommandManager(VehiclesCollecton vehiclesCollecton, ConsoleReader consoleReader) {
        this.commands = new HashMap<>(); //карта команд
        this.commandHistory = new LinkedList<>();
        this.vehicles = vehiclesCollecton.getVehicles() != null ? vehiclesCollecton.getVehicles() : new HashSet<>();
        registerCommands(vehiclesCollecton, consoleReader);
    }

    //регистрация команд
    private void registerCommands(VehiclesCollecton vehiclesCollecton, ConsoleReader consoleReader) {
        commands.put("help", new Help());
        commands.put("info", new Info(vehicles));
        commands.put("show", new Show(vehicles));
        commands.put("add", new Add(vehicles,consoleReader));
        commands.put("updateid", new UpdateID(vehicles, consoleReader));
        commands.put("removebyid", new RemoveByID(vehicles));
        commands.put("clear", new Clear(vehicles));
        commands.put("save", new Save(vehiclesCollecton));
        commands.put("executescript", new ExecuteScript(this));
        commands.put("exit", new Exit());
        commands.put("addifmin", new AddIfMin(vehicles, consoleReader));
        commands.put("removegreater", new RemoveGreater(vehicles, consoleReader));
        commands.put("sumofenginepower", new SumOfEnginePower(vehicles));
        commands.put("averageofenginepower", new AverageOfEnginePower(vehicles));
        commands.put("printuniquefueltype", new PrintUniqueFuelType(vehicles));
        commands.put("history", new History(commandHistory));
    }

    // Метод для выполнения команды и возврата значения, завершать ли программу
    public boolean executeCommand(String inputLine) {
        String[] parts = inputLine.trim().split(" ", 2);
        /*
        trim() удаляет пробелы в начале и в конце строки
        split(" ") разделяет строку на массив строк, используя пробел как разделитель
        Число 2 в параметре метода split указывает, что строка будет разделена на не более чем 2 части =>
        => всё, что идёт после первого пробела, будет считаться аргументом команды.
        "updateID 123" => массив ["updateID", "123"].
        "show" => массив ["show"]
         */

        String commandName = parts[0].toLowerCase();
        /* parts[0] - первый элемент массива (название)
        toLoweCase() - преобразует строку в нижний регистр
        "UPDATEID 123" => после toLoweCase() commandName будет "updateid".
         */

        String argument = parts.length > 1 ? parts[1].trim() : "";
        /*
        parts.length > 1 - проверка, есть ли в массиве parts вторая часть.
        Если длина массива больше 1, это значит, что команда была введена с аргументом
        Если аргумент существует, то он извелкается при помощи parts[1]
        и в нем удаляются лишние пробелы при помощи trim()
        "" - Если команда введена без аргумента, то вместо аргумента присваивается пустая строка (""),
         чтобы не было NullPointerException
         */

        //проверка на существование команды
        Command command = commands.get(commandName);
        if (command == null) {
            System.out.println("Команда не найдена " + commandName);
            return false;
        }

        //проверка на наличие аргумента
        if (commandRequiresArgument(commandName)) {
            // Если команда требует аргумент, но он не передан
            if (argument.isEmpty()) {
                System.out.println("Команда " + commandName + " требует аргумент");
                return false;
            }
        } else {
            // Если команда не требует аргумент, но он передан
            if (!argument.isEmpty()) {
                System.out.println("Команда " + commandName + " не принимает аргументов");
                return false;
            }
        }
        recordCommand(commandName); //запись существующей команды в историю
        command.execute(argument);  // Выполняем команду
        return false;
    }

    //метод для проверки наличия аргументов у команд
    private boolean commandRequiresArgument(String commandName) {
        Set<String> commandsWithArgs = Set.of("updateid", "removebyid", "executescript");
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
