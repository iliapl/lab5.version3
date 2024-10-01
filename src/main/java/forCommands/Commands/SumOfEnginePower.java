package forCommands.Commands;

import forCommands.Command;
import forVehicles.Vehicle;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class SumOfEnginePower implements Command {
    private final Set<Vehicle> vehicles;

    @Override
    public void execute(String argument) {
        if (vehicles.isEmpty()) {
            System.out.println("Коллекция пуста. Сумма значения enginePower: 0.");
            return;
        }
        int totalEnginePower = vehicles.stream().mapToInt(Vehicle::getEnginePower).sum();
        System.out.println("Сумма значений поля enginePower для всех элементов коллекции: " + totalEnginePower);
    }
}
