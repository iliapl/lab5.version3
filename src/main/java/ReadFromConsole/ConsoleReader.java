package ReadFromConsole;

import forVehicles.Coordinates;
import forVehicles.FuelType;
import forVehicles.Vehicle;
import forVehicles.VehicleType;

import java.util.Scanner;

public class ConsoleReader {
    private final Scanner scanner;
    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }
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
        if (name.isEmpty()) {
            System.out.println("Имя не должно быть пустым.");
            return readNameFromConsole();
        }
        return name;
    }
    private Coordinates readCoordinatesFromConsole() {
        System.out.println("Введите координату х:");
        long x = readLongInput();
        System.out.println("Введите координату y (0 < y <= 597):");
        float y;
        while (true) {
            y = readFloatInput();
            if (y > 0 && y <= 597) {
                break;
            } else {
                System.out.println("Введите корректное значение для y (0 < y <= 597).");
            }
        }
        return new Coordinates(x, y);
    }
    private int readEnginePowerFromConsole() {
        System.out.println("Введите значение enginePower (должно быть больше 0):");
        int enginePower;
        while (true) {
            enginePower = readIntInput();
            if (enginePower > 0) {
                break;
            } else {
                System.out.println("EnginePower должно быть больше нуля.");
            }
        }
        return enginePower;
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
            int choice = readIntInput();
            if (choice >= 0 && choice < enumValues.length) {
                return enumValues[choice];
            } else {
                System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // Вспомогательные методы для ввода данных
    private long readLongInput() {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }

    private float readFloatInput() {
        while (true) {
            try {
                return Float.parseFloat(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }

    private int readIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
            }
        }
    }
}
