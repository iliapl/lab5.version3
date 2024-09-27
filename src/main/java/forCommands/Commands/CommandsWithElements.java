
package forCommands.Commands;

import ReadFromConsole.ConsoleReader;
import forVehicles.Coordinates;
import forVehicles.FuelType;
import forVehicles.Vehicle;
import forVehicles.VehicleType;

import java.util.Set;

public sealed class CommandsWithElements permits Add, AddIfMin, RemoveGreater, UpdateID {
    protected final Set<Vehicle> vehicles;
    protected ConsoleReader consoleReader;
    public CommandsWithElements(Set<Vehicle> vehicles, ConsoleReader consoleReader) {
        this.vehicles = vehicles;
        this.consoleReader = consoleReader;
    }

    /* getVehicle и readVehicleFromArguments позволяют создать объекта Vehicle двумя способами:
    1) через строковые аргументы, например add matt 1 2 3 1 1)
    2) "в интерактивном режиме" - т.е. поочередно
     */

    //метод, позволяющий выбрать способ создания объекта
    protected Vehicle getVehicle(String argument) {
        Vehicle newVehicle;
        if (!argument.isEmpty()) {
            String[] args = argument.split(" "); // split разбивает строку на массив строк по пробелам
            newVehicle = readVehicleFromArguments(args);
        } else {
            newVehicle = consoleReader.readVehicleFromConsole();
        }
        return newVehicle;
    }

    //метод, позволяющий преобразовать аргументы из строки в объект Vehicle
    private Vehicle readVehicleFromArguments(String[] args) {
        if (args.length == 6) {
            try {
                //извлекаем аргументы и сохраняем в соответствующие переменные
                String name = args[0];
                long x = Long.parseLong(args[1]);
                float y = Float.parseFloat(args[2]);
                int enginePower = Integer.parseInt(args[3]);
                int vehicleTypeOrdinal = Integer.parseInt(args[4]);
                int fuelTypeOrdinal = Integer.parseInt(args[5]);

                Coordinates coordinates = new Coordinates(x, y);
                VehicleType vehicleType = VehicleType.values()[vehicleTypeOrdinal]; //чтобы выбрать значения из enum
                FuelType fuelType = FuelType.values()[fuelTypeOrdinal];

                return new Vehicle(name, coordinates, enginePower, vehicleType, fuelType);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: неверный формат аргументов. Проверьте числа.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка: неверные значения для VehicleType или FuelType.");
            }
        } else {
            System.out.println("Неверное количество аргументов. Ожидалось 6 аргументов. Переходим в интерактивный режим...");
            return consoleReader.readVehicleFromConsole();
        }
        return null;
    }
}
