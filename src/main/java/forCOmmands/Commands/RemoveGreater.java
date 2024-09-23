package forCOmmands.Commands;

import forCOmmands.Command;
import forVehicles.Vehicle;
import forVehicles.VehicleReader;

import java.util.Set;

public class RemoveGreater implements Command {
    private final Set<Vehicle> vehicles;
    private final VehicleReader vehicleReader;

    public RemoveGreater(Set<Vehicle> vehicles, VehicleReader vehicleReader) {
        this.vehicles = vehicles;
        this.vehicleReader = vehicleReader;
    }

    @Override
    public void execute(String argument){
        try {
            Vehicle referenceVehicle = vehicleReader.readVehicleFromConsole();
            int referenceEnginePower = referenceVehicle.getEnginePower();
            int initialSize = vehicles.size();
            vehicles.removeIf(vehicle -> vehicle.getEnginePower() > referenceEnginePower);
            int elementsRemoved = initialSize - vehicles.size();
            if (elementsRemoved > 0) {
                System.out.println("Удалено " + elementsRemoved + " элементов, превышающих заданный.");
            } else {
                System.out.println("Нет элементов, превышающих заданный.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при удалении элементов: " + e.getMessage());
        }
    }
}