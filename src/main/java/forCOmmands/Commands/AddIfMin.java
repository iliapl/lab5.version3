package forCOmmands.Commands;

import forCOmmands.Command;
import forVehicles.Vehicle;
import forVehicles.ConsoleReader;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

public class AddIfMin implements Command {
    private final Set<Vehicle> vehicles;
    private final ConsoleReader consoleReader;

    public AddIfMin(Set<Vehicle> vehicles, ConsoleReader consoleReader) {
        this.vehicles = vehicles;
        this.consoleReader = consoleReader;
    }

    @Override
    public void execute(String argument) {
        try {
            Vehicle newVehicle = consoleReader.readVehicleFromConsole();
            Optional<Vehicle> minVehicle = vehicles.stream().min(Comparator.comparingInt(Vehicle::getEnginePower));
            if (minVehicle.isPresent()) {
                int minEnginePower = minVehicle.get().getEnginePower();
                if (newVehicle.getEnginePower() < minEnginePower) {
                    vehicles.add(newVehicle);
                    System.out.println("Добавлен новый элемент, так как его enginePower меньше, чем у наименьшего элемента.");
                } else {
                    System.out.println("Новый элемент не добавлен, так как его enginePower не меньше минимального.");
                }
            } else {
                vehicles.add(newVehicle);
                System.out.println("Коллекция пуста. Добавлен первый элемент.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении элемента: " + e.getMessage());
        }
    }
}