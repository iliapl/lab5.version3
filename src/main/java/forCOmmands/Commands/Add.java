package forCOmmands.Commands;

import forCOmmands.Command;
import forVehicles.Vehicle;
import forVehicles.VehicleReader;
import util.FileRead;

import java.util.HashSet;
import java.util.Set;

public class Add implements Command {
    private final Set<Vehicle> vehicles;
    private VehicleReader vehicleReader;

    public Add(Set<Vehicle> vehicles, VehicleReader vehicleReader) {
        this.vehicles = vehicles;
        this.vehicleReader = vehicleReader;
    }

    @Override
    public void execute(String argument) {
        try {
            Vehicle newVehicle = vehicleReader.readVehicleFromConsole();
            vehicles.add(newVehicle);
            System.out.println("Добавлен новый транспорт: " + newVehicle.vehicleToString());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при добавлении нового транспорта: " + e.getMessage());
        }
    }
}
