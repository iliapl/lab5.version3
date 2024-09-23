package forCOmmands;

import forVehicles.Vehicle;
import forVehicles.VehicleReader;
import forVehicles.VehiclesCollecton;
import util.WriteFileToXML;
import forCOmmands.Commands.*;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.*;

public class CommandManager {
    private final Map<String, Command> commands;  // Карта команд
    private final Set<Vehicle> vehicles;
    private final Deque<String> commandHistory;
    private final int maxHistorySize = 7;
    private final java.time.ZonedDateTime creationDate;

    public CommandManager(VehiclesCollecton vehiclesCollecton, VehicleReader vehicleReader, WriteFileToXML writeFileToXML, File file) {
        this.commands = new HashMap<>();  // Инициализация карты команд
        this.commandHistory = new LinkedList<>();
        this.vehicles = vehiclesCollecton.getVehicles() != null ? vehiclesCollecton.getVehicles() : new HashSet<>();
        creationDate = java.time.ZonedDateTime.now();

        // Регистрация всех команд
        registerCommands(vehiclesCollecton, vehicleReader, writeFileToXML, file, creationDate);
    }

    // Метод для регистрации команд
    private void registerCommands(VehiclesCollecton vehiclesCollecton, VehicleReader vehicleReader, WriteFileToXML writeFileToXML, File file, ZonedDateTime creationDate) {
        commands.put("help", new Help());
        commands.put("info", new Info(vehicles, creationDate));
        commands.put("show", new Show(vehicles));
        commands.put("add", new Add(vehicles, vehicleReader));
        commands.put("updateID", new UpdateID(vehicles, vehicleReader));
        commands.put("removeByID", new RemoveByID(vehicles));
        commands.put("clear", new Clear(vehicles));
        commands.put("save", new Save(vehiclesCollecton,writeFileToXML));
        commands.put("executeScript", new ExecuteScript(commands));  // Передача всех команд для скрипта
        commands.put("exit", new Exit());
        commands.put("add_if_min", new AddIfMin(vehicles, vehicleReader));
        commands.put("remove_greater", new RemoveGreater(vehicles, vehicleReader));
        commands.put("sum_of_engine_power", new SumOfEnginePower(vehicles));
        commands.put("average_of_engine_power", new AverageOfEnginePower(vehicles));
        commands.put("print_unique_fuel_type", new PrintUniqueFuelType(vehicles));
    }

    // Метод для выполнения команды и возврата значения, завершать ли программу
    public boolean executeCommand(String inputLine) {
        // Добавляем команду в историю
        recordCommand(inputLine);

        if (inputLine == null || inputLine.trim().isEmpty()) {
            System.out.println("Введите команду.");
            return false;
        }

        // Разделение строки на команду и аргументы
        String[] parts = inputLine.trim().split(" ", 2);
        String commandName = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        // Поиск команды в карте
        Command command = commands.get(commandName);
        if (command != null) {
            // Выполняем команду
            command.execute(arguments);
            return false;  // Программа продолжается
        } else {
            System.out.println("Команда не найдена: " + commandName);
            return false;  // Программа продолжается
        }
    }

    // Метод для записи команды в историю
    private void recordCommand(String command) {
        if (commandHistory.size() >= maxHistorySize) {
            commandHistory.pollFirst();  // Удаляем самую старую команду
        }
        commandHistory.addLast(command);
    }
}
