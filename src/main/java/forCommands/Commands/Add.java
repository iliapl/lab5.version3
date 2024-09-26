package forCommands.Commands;

import ReadFromConsole.ConsoleReader;
import forCommands.CommandsWithElements;
import forVehicles.Vehicle;

import java.util.Set;
import java.util.Scanner;

public class Add extends CommandsWithElements {

    public Add(Set<Vehicle> vehicles, Scanner scanner, ConsoleReader consoleReader) {
        super(vehicles, scanner, consoleReader);
    }

    @Override
    public void execute(String argument) {
        Vehicle newVehicle = getVehicle(argument);
        if (newVehicle != null) {
            vehicles.add(newVehicle);
            System.out.println("Добавлен новый транспорт: " + newVehicle.vehicleToString());
        } else {
            System.out.println("Ошибка при добавлении транспорта.");
        }
    }
}