package ReadFromConsole;

import Utilities.ReadNumberInput;
import Utilities.Validate;
import forVehicles.Coordinates;
import forVehicles.FuelType;
import forVehicles.Vehicle;
import forVehicles.VehicleType;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class ConsoleReader implements Validate, ReadNumberInput {
    private final Scanner scanner;

    public Vehicle readVehicleFromConsole() {
        String name = readNameFromConsole();
        Coordinates coordinates = readCoordinatesFromConsole();
        int enginePower = readEnginePowerFromConsole();
        VehicleType vehicleType = readVehicleTypeFromConsole();
        FuelType fuelType = readFuelTypeFromConsole();
        return new Vehicle(name, coordinates, enginePower, vehicleType, fuelType);
    }

    private String readNameFromConsole() {
        System.out.println("Введите имя:");
        String name = scanner.nextLine().trim();
        try {
            return validateName(name);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readNameFromConsole();
        }
    }

    private Coordinates readCoordinatesFromConsole() {
        System.out.println("Введите координату х:");
        long x = readLongInput(scanner);
        System.out.println("Введите координату y (0 < y <= 597):");
        float y = readFloatInput(scanner);
        try {
            return validateCoordinates(x, y);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readCoordinatesFromConsole();
        }
    }

    private int readEnginePowerFromConsole() {
        System.out.println("Введите значение enginePower (должно быть больше 0):");
        int enginePower = readIntInput(scanner);
        try {
            return validateEnginePower(enginePower);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readEnginePowerFromConsole();
        }
    }

    private VehicleType readVehicleTypeFromConsole() {
        VehicleType[] types = VehicleType.values();
        System.out.println("Выберите тип транспорта:");
        for (int i = 0; i < types.length; i++) {
            System.out.println(i + " - " + types[i]);
        }
        return readEnumChoice(types);
    }

    private FuelType readFuelTypeFromConsole() {
        FuelType[] fuelTypes = FuelType.values();
        System.out.println("Выберите тип топлива:");
        for (int i = 0; i < fuelTypes.length; i++) {
            System.out.println(i + " - " + fuelTypes[i]);
        }
        return readEnumChoice(fuelTypes);
    }

    // Универсальный метод для чтения enum (VehicleType и FuelType)
    private <T extends Enum<T>> T readEnumChoice(T[] enumValues) {
        while (true) {
            int choice = readIntInput(scanner);
            if (choice >= 0 && choice < enumValues.length) {
                return enumValues[choice];
            } else {
                System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
