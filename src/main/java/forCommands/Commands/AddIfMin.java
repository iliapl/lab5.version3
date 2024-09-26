package forCommands.Commands;

import ReadFromConsole.ConsoleReader;
import forCommands.CommandsWithElements;
import forVehicles.Vehicle;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.Scanner;

public class AddIfMin extends CommandsWithElements {

    public AddIfMin(Set<Vehicle> vehicles, Scanner scanner, ConsoleReader consoleReader) {
        super(vehicles, scanner, consoleReader);
    }

    @Override
    public void execute(String argument) {
        Vehicle newVehicle = getVehicle(argument);
        if (newVehicle != null) {
            Optional<Vehicle> minVehicle = vehicles.stream().min(Comparator.comparingInt(Vehicle::getEnginePower));
            if (minVehicle.isPresent()) {
                int minEnginePower = minVehicle.get().getEnginePower();
                // Добавляем, если enginePower нового объекта меньше минимального
                if (newVehicle.getEnginePower() < minEnginePower) {
                    vehicles.add(newVehicle);
                    System.out.println("Добавлен новый элемент, так как его enginePower меньше, чем у наименьшего элемента.");
                } else {
                    System.out.println("Новый элемент не добавлен, так как его enginePower не меньше минимального.");
                }
            } else {
                // Если коллекция пуста, добавляем элемент
                vehicles.add(newVehicle);
                System.out.println("Коллекция пуста. Добавлен первый элемент.");
            }
        } else {
            System.out.println("Ошибка при добавлении элемента.");
        }
    }
}
