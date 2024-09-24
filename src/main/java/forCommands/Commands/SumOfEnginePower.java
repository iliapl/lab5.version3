package forCommands.Commands;

import forCommands.Command;
import forVehicles.Vehicle;

import java.util.Set;

public class SumOfEnginePower implements Command {
    private final Set<Vehicle> vehicles;
    public SumOfEnginePower(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
    @Override
    public void execute(String argument){
        if (vehicles.isEmpty()) {
            System.out.println("Коллекция пуста. Сумма значения enginePower: 0.");
            return;
        }
        int totalEnginePower = vehicles.stream().mapToInt(Vehicle::getEnginePower).sum();
        System.out.println("Сумма значений поля enginePower для всех элементов коллекции: " + totalEnginePower);
    }
}
