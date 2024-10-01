package forCommands.Commands;

import forCommands.Command;
import forVehicles.FuelType;
import forVehicles.Vehicle;
import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PrintUniqueFuelType implements Command {
    private final Set<Vehicle> vehicles;

    @Override
    public void execute(String argument) {
        if (vehicles.isEmpty()) {
            System.out.println("Коллекция пуста. Нет уникальных значений fuelType.");
            return;
        }
        Set<FuelType> uniqueFuelTypes = vehicles.stream().map(Vehicle::getFuelType).collect(Collectors.toSet());
        if (uniqueFuelTypes.isEmpty()) {
            System.out.println("Нет уникальных значений fuelType.");
        } else {
            System.out.println("Уникальные значения fuelType:");
            uniqueFuelTypes.forEach(System.out::println);
        }
    }
}
