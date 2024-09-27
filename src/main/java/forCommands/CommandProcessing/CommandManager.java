package forCommands.CommandProcessing;

import ReadFromConsole.ConsoleReader;
import Utilities.EnvDoing;
import forCommands.Commands.Add;
import forCommands.Command;
import forCommands.Commands.*;
import forFile.WriteFileToXML;
import forVehicles.Vehicle;
import forVehicles.VehiclesCollecton;
import java.util.*;

public class CommandManager {
    private final Map<String, Command> commands;
    private final Set<Vehicle> vehicles;
    private final Deque<String> commandHistory;
    private final WriteFileToXML writeFileToXML;
    private final EnvDoing envDoing;
    private final ConsoleReader consoleReader;

    public CommandManager(VehiclesCollecton vehiclesCollecton, ConsoleReader consoleReader, WriteFileToXML writeFileToXML, EnvDoing envDoing) {
        this.commands = new HashMap<>();
        this.commandHistory = new LinkedList<>();
        this.writeFileToXML = writeFileToXML;
        this.consoleReader = consoleReader;
        this.envDoing = envDoing;
        this.vehicles = vehiclesCollecton.getVehicles() != null ? vehiclesCollecton.getVehicles() : new HashSet<>();
        registerCommands();
    }

    //регистрация команд
    private void registerCommands() {
        commands.put("help", new Help());
        commands.put("info", new Info(vehicles));
        commands.put("show", new Show(vehicles));
        commands.put("add", new Add(vehicles, consoleReader));
        commands.put("updateid", new UpdateID(vehicles, consoleReader));
        commands.put("removebyid", new RemoveByID(vehicles));
        commands.put("clear", new Clear(vehicles));
        commands.put("save", new Save(writeFileToXML, envDoing));
        commands.put("executescript", new ExecuteScript(new ExecuteCommands(this)));
        commands.put("exit", new Exit());
        commands.put("addifmin", new AddIfMin(vehicles, consoleReader));
        commands.put("removegreater", new RemoveGreater(vehicles, consoleReader));
        commands.put("sumofenginepower", new SumOfEnginePower(vehicles));
        commands.put("averageofenginepower", new AverageOfEnginePower(vehicles));
        commands.put("printuniquefueltype", new PrintUniqueFuelType(vehicles));
        commands.put("history", new History(commandHistory));
    }

    //метод для записи команд в историю
    protected void recordCommand(String command) {
        int maxHistorySize = 7;
        if (commandHistory.size() >= maxHistorySize) {
            commandHistory.pollFirst();
        }
        commandHistory.addLast(command);
    }
    protected Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
