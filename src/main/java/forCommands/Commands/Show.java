package forCommands.Commands;

import forCommands.Command;
import forVehicles.Vehicle;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class Show implements Command {
    private final Set<Vehicle> vehicles;

    @Override
    public void execute(String argument) {
        if (vehicles.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }
        System.out.println("Элементы коллекции (отсортированные по engine power):");
        vehicles.stream()
                .sorted()
                .forEach(vehicle -> System.out.println(vehicle.toString()));
    }
}
