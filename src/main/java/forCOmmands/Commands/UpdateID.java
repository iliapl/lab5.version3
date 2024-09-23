package forCOmmands.Commands;

import forCOmmands.Command;
import forVehicles.Vehicle;
import forVehicles.VehicleReader;

import java.util.Set;

public class UpdateID implements Command {
    private final Set<Vehicle> vehicles;
    private final VehicleReader vehicleReader;
    public UpdateID (Set<Vehicle> vehicles, VehicleReader vehicleReader){
        this.vehicles = vehicles;
        this.vehicleReader = vehicleReader;
    }
    @Override
    public void execute(String argument) {
        try {
            int id = Integer.parseInt(argument.trim());
            Vehicle vehicleToUpdate = null;
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getId() == id) {
                    vehicleToUpdate = vehicle;
                    break;
                }
            }
            if (vehicleToUpdate == null) {
                System.out.println("Элемент с ID " + id + " не найден.");
                return;
            }
            Vehicle newVehicleData = vehicleReader.readVehicleFromConsole();
            vehicleToUpdate.update(newVehicleData);
            System.out.println("Элемент с ID " + id + " обновлен.");

        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат ID. Введите целое число.");
        } catch (Exception e) {
            System.out.println("Ошибка при обновлении элемента: " + e.getMessage());
        }
    }
}