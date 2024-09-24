package forCommands.Commands;

import forCommands.Command;
import forVehicles.Vehicle;

import java.util.Set;

public class AverageOfEnginePower implements Command {
    private final Set<Vehicle> vehicles;
    public AverageOfEnginePower(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
    @Override
    public void execute(String argument) {
        try {
            if (vehicles.isEmpty()) {
                System.out.println("Коллекция пуста. Среднее значение enginePower: 0.");
                return;
            }
            int totalEnginePower = vehicles.stream().mapToInt(Vehicle::getEnginePower).sum();
            long count = vehicles.size();
            double averageEnginePower = (double) totalEnginePower / count;
            System.out.printf("Среднее значение enginePower для всех элементов коллекции: %.2f%n", averageEnginePower);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при добавлении нового транспорта: " + e.getMessage());
        }
    }
}
