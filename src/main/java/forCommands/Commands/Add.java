package forCommands.Commands;

import ReadFromConsole.ConsoleReader;
import forCommands.Command;
import forVehicles.Vehicle;

import java.util.Set;

public final class Add extends CommandsWithElements implements Command {
    public Add(Set<Vehicle> vehicles, ConsoleReader consoleReader) {
        super(vehicles, consoleReader);
    }

    @Override
    public void execute(String argument) {
        Vehicle newVehicle = getVehicle(argument);
        if (newVehicle != null) {
            vehicles.add(newVehicle);
            System.out.println("Добавлен новый транспорт: " + newVehicle);
        } else {
            System.out.println("Ошибка при добавлении транспорта.");
        }
    }
}