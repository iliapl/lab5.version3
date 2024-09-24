package forCommands.Commands;

import forCommands.Command;
import forVehicles.Vehicle;
import ReadFromConsole.ConsoleReader;

import java.util.Set;

public class Add implements Command {
    private final Set<Vehicle> vehicles;
    private final ConsoleReader consoleReader;
    public Add(Set<Vehicle> vehicles, ConsoleReader consoleReader) {
        this.vehicles = vehicles;
        this.consoleReader = consoleReader;
    }
    @Override
    public void execute(String argument) {
        try {
            Vehicle newVehicle = consoleReader.readVehicleFromConsole();
            vehicles.add(newVehicle);
            System.out.println("Добавлен новый транспорт: " + newVehicle.vehicleToString());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при добавлении нового транспорта: " + e.getMessage());
        }
    }
}
