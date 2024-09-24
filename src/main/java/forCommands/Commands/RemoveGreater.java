package forCommands.Commands;

import forCommands.Command;
import forVehicles.Vehicle;
import ReadFromConsole.ConsoleReader;

import java.util.Set;

public class RemoveGreater implements Command {
    private final Set<Vehicle> vehicles;
    private final ConsoleReader consoleReader;
    public RemoveGreater(Set<Vehicle> vehicles, ConsoleReader consoleReader) {
        this.vehicles = vehicles;
        this.consoleReader = consoleReader;
    }
    @Override
    public void execute(String argument){
        try {
            Vehicle referenceVehicle = consoleReader.readVehicleFromConsole();
            int referenceEnginePower = referenceVehicle.getEnginePower();
            int initialSize = vehicles.size();
            vehicles.removeIf(vehicle -> vehicle.getEnginePower() > referenceEnginePower);
            int elementsRemoved = initialSize - vehicles.size();
            if (elementsRemoved > 0) {
                System.out.println("Удалено " + elementsRemoved + " элементов, превышающих заданный.");
            } else {
                System.out.println("Нет элементов, превышающих заданный.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при удалении элементов: " + e.getMessage());
        }
    }
}