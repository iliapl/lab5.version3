package forCommands.Commands;

import forCommands.Command;
import forVehicles.Vehicle;

import java.util.Set;

public class Show implements Command {
    private final Set<Vehicle> vehicles;
    public Show (Set<Vehicle> vehicles){
        this.vehicles = vehicles;
    }
    @Override
    public void execute(String argument){
        if (vehicles.isEmpty()) {
            System.out.println("Коллекция пуста.");
            return;
        }
        System.out.println("Элементы коллекции (отсортированные по engine power):");
        vehicles.stream()
                .sorted()
                .forEach(vehicle -> System.out.println(vehicle.vehicleToString()));
    }
}
