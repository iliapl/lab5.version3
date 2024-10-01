package forCommands.Commands;

import forCommands.Command;
import forVehicles.Vehicle;
import lombok.AllArgsConstructor;

import java.util.Set;
@AllArgsConstructor
public class Clear implements Command {
    private final Set<Vehicle> vehicles;

    @Override
    public void execute(String argument) {
        if (vehicles.isEmpty()) {
            System.out.println("Коллекция уже пустая, нечего очищать.");
            return;
        }
        try {
            vehicles.clear();
            System.out.println("Коллекция была успешно очищена.");
        } catch (Exception e) {
            System.out.println("Произошла ошибка при очистке коллекции: " + e.getMessage());
        }
    }
}
