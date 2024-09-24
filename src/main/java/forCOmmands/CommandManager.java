package forCOmmands;

import forVehicles.Vehicle;
import forVehicles.ConsoleReader;
import forVehicles.VehiclesCollecton;
import util.EnvDoing;
import util.WriteFileToXML;
import forCOmmands.Commands.*;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.*;

public class CommandManager {
    private final Map<String, Command> commands;
    private final Set<Vehicle> vehicles;
    private final Deque<String> commandHistory;
    private final int maxHistorySize = 7;
    private final java.time.ZonedDateTime creationDate;
    private final EnvDoing envDoing;

    public CommandManager(VehiclesCollecton vehiclesCollecton, ConsoleReader consoleReader, WriteFileToXML writeFileToXML, File file) {
        this.commands = new HashMap<>();  // Инициализация карты команд
        this.commandHistory = new LinkedList<>();
        this.vehicles = vehiclesCollecton.getVehicles() != null ? vehiclesCollecton.getVehicles() : new HashSet<>();
        creationDate = java.time.ZonedDateTime.now();
        this.envDoing = new EnvDoing();

        // Регистрация всех команд
        registerCommands(vehiclesCollecton, consoleReader, writeFileToXML, creationDate);
    }
    private void registerCommands(VehiclesCollecton vehiclesCollecton, ConsoleReader consoleReader, WriteFileToXML writeFileToXML, ZonedDateTime creationDate) {
        commands.put("help", new Help());
        commands.put("info", new Info(vehicles, creationDate));
        commands.put("show", new Show(vehicles));
        commands.put("add", new Add(vehicles,consoleReader));
        commands.put("updateid", new UpdateID(vehicles, consoleReader));
        commands.put("removebyid", new RemoveByID(vehicles));
        commands.put("clear", new Clear(vehicles));
        commands.put("save", new Save(vehiclesCollecton,writeFileToXML, envDoing));
        commands.put("executescript", new ExecuteScript(commands));
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
        String commandName = parts[0].toLowerCase();  // Преобразуем в нижний регистр
        String argument = parts.length > 1 ? parts[1].trim() : "";

        Command command = commands.get(commandName);
        if (command == null) {
            System.out.println("Команда не найдена: " + commandName);
            return false;
        }

        // Записываем только существующую команду в историю
        recordCommand(commandName);
        command.execute(argument);  // Выполняем команду
        return false;
    }
    private void recordCommand(String command) {
        if (commandHistory.size() >= maxHistorySize) {
            commandHistory.pollFirst();
        }
        commandHistory.addLast(command);
    }
}
