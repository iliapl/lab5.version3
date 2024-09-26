package forCommands.Commands;

import ReadFromConsole.ConsoleReader;
import forCommands.CommandsWithElements;
import forVehicles.Vehicle;

import java.util.Set;
import java.util.Scanner;

public class RemoveGreater extends CommandsWithElements {

    public RemoveGreater(Set<Vehicle> vehicles, Scanner scanner, ConsoleReader consoleReader) {
        super(vehicles, scanner, consoleReader);
    }

    @Override
    public void execute(String argument) {
        Vehicle referenceVehicle = getVehicle(argument);
        if (referenceVehicle != null) {
            int referenceEnginePower = referenceVehicle.getEnginePower();
            int initialSize = vehicles.size();

            // Удаляем все элементы, у которых enginePower больше, чем у referenceVehicle
            vehicles.removeIf(vehicle -> vehicle.getEnginePower() > referenceEnginePower);

            int elementsRemoved = initialSize - vehicles.size();
            if (elementsRemoved > 0) {
                System.out.println("Удалено " + elementsRemoved + " элементов, превышающих заданный.");
            } else {
                System.out.println("Нет элементов, превышающих заданный.");
            }
        } else {
            System.out.println("Ошибка при удалении элементов.");
        }
    }
}
