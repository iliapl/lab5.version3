package forCommands.Commands;

import ReadFromConsole.ConsoleReader;
import forCommands.Command;
import forVehicles.Vehicle;

import java.util.Set;

public final class RemoveGreater extends CommandsWithElements implements Command {
    public RemoveGreater(Set<Vehicle> vehicles, ConsoleReader consoleReader) {
        super(vehicles, consoleReader);
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
