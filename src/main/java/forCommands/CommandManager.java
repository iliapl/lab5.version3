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
        commands.put("add", new Add(vehicles,new Scanner(System.in), consoleReader));
        commands.put("updateid", new UpdateID(vehicles,new Scanner(System.in), consoleReader));
        commands.put("removebyid", new RemoveByID(vehicles));
        commands.put("clear", new Clear(vehicles));
        commands.put("save", new Save(vehiclesCollecton));
        commands.put("executescript", new ExecuteScript(new ExecuteCommands(this)));
        commands.put("exit", new Exit());
        commands.put("addifmin", new AddIfMin(vehicles,new Scanner(System.in), consoleReader));
        commands.put("removegreater", new RemoveGreater(vehicles,new Scanner(System.in), consoleReader));
        commands.put("sumofenginepower", new SumOfEnginePower(vehicles));
        commands.put("averageofenginepower", new AverageOfEnginePower(vehicles));
        commands.put("printuniquefueltype", new PrintUniqueFuelType(vehicles));
        commands.put("history", new History(commandHistory));
    }
    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
