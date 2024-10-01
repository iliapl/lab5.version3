package forCommands.Commands;

import ReadFromConsole.ConsoleReader;
import forCommands.Command;
import forVehicles.Vehicle;

import java.util.Set;

public final class UpdateID extends CommandsWithElements implements Command {
    public UpdateID(Set<Vehicle> vehicles, ConsoleReader consoleReader) {
        super(vehicles, consoleReader);
    }

    @Override
    public void execute(String argument) {
        try {
            // Разделяем строку аргументов. Ожидается как минимум 1 аргумент — id
            String[] args = argument.split(" ");

            // Проверяем, что аргумент id был передан
            if (args.length < 1) {
                System.out.println("Ошибка: не передан ID.");
                return;
            }

            // Извлекаем ID объекта, который нужно обновить
            int id = Integer.parseInt(args[0].trim());

            // Ищем объект по ID
            Vehicle vehicleToUpdate = vehicles.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
            if (vehicleToUpdate == null) {
                System.out.println("Транспорт с ID " + id + " не найден.");
                return;
            }

            String remainingArgs = args.length > 1 ? String.join(" ", args).substring(args[0].length()).trim() : "";
            Vehicle updatedVehicle = getVehicle(remainingArgs);  // Используем метод getVehicle

            // Обновляем объект, если данные введены корректно
            if (updatedVehicle != null) {
                vehicleToUpdate.setName(updatedVehicle.getName());
                vehicleToUpdate.setCoordinates(updatedVehicle.getCoordinates());
                vehicleToUpdate.setVehicleType(updatedVehicle.getVehicleType());
                vehicleToUpdate.setFuelType(updatedVehicle.getFuelType());
                System.out.println("Транспорт с ID " + id + " был обновлён.");
            } else {
                System.out.println("Ошибка при обновлении транспорта.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректный формат ID.");
        }
    }
}
