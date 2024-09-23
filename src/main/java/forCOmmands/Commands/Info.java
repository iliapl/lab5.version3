package forCOmmands.Commands;

import forCOmmands.Command;
import forVehicles.Vehicle;

import java.time.ZonedDateTime;
import java.util.Set;

public class Info implements Command {
    private final Set<Vehicle> vehicles;
    private final ZonedDateTime creationDate;

    public Info(Set<Vehicle> vehicles, ZonedDateTime creationDate) {
        this.vehicles = vehicles;
        this.creationDate = creationDate;
    }

    @Override
    public void execute(String argument) {
        System.out.println("Информация о коллекции:");
        System.out.println("Тип коллекции: " + vehicles.getClass().getSimpleName());
        System.out.println("Дата инициализации: " + creationDate);
        System.out.println("Количество элементов: " + vehicles.size());
    }
}
