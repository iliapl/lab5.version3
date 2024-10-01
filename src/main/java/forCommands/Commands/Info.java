package forCommands.Commands;

import forCommands.Command;
import forVehicles.Vehicle;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Set;

@AllArgsConstructor
public class Info implements Command {
    private final Set<Vehicle> vehicles;

    @Override
    public void execute(String argument) {
        System.out.println("Информация о коллекции:");
        System.out.println("Тип коллекции: " + vehicles.getClass().getSimpleName());
        System.out.println("Дата инициализации: " + ZonedDateTime.now());
        System.out.println("Количество элементов: " + vehicles.size());
    }
}
