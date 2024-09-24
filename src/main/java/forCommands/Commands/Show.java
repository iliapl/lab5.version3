package forCommands.Commands;

import forCommands.Command;
import forVehicles.Vehicle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
        Comparator<Vehicle> comparator = Comparator.comparing(Vehicle::getName);
        List<Vehicle> sortedList = new ArrayList<>(vehicles);
        sortedList.sort(comparator);
        System.out.println("Элементы коллекции (отсортированные по имени):");
        for (Vehicle vehicle : sortedList) {
            System.out.println(vehicle.vehicleToString());
        }
    }
}
