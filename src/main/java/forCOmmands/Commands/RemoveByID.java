package forCOmmands.Commands;

import forCOmmands.Command;
import forVehicles.Vehicle;
import util.FileRead;

import java.util.Set;

public class RemoveByID implements Command {
    private final Set<Vehicle> vehicles;
    public RemoveByID(Set<Vehicle> vehicles){
        this.vehicles = vehicles;
    }
    @Override
    public void execute(String argument){
        try {
            int id = Integer.parseInt(argument.trim());
            boolean removed = vehicles.removeIf(vehicle -> vehicle.getId() == id);
            if (removed) {
                System.out.println("Элемент с ID " + id + " был успешно удален.");
            } else {
                System.out.println("Элемент с ID " + id + " не найден.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат ID. Введите целое число.");
        } catch (Exception e) {
            System.out.println("Ошибка при удалении элемента: " + e.getMessage());
        }
    }
}
