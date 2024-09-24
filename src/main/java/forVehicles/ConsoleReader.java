package forVehicles;

import java.util.Scanner;

public class ConsoleReader {
    private final Scanner scanner;

    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Vehicle readVehicleFromConsole() {
        return new Vehicle(readNamefromConsole(), readCordinatesFromConsole(), readEnginePowerFromConsole(), readVehicleTypeFromConsole(), readFuelTypeFromConsole());
    }

    private String readNamefromConsole() {
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        if (name == null || name.isEmpty()) {
            System.out.println("Вы не ввели имя");
            return readNamefromConsole();
        } else {
            return name;
        }
    }

    private Coordinates readCordinatesFromConsole() {
        System.out.println("Введите координату х");
        long x;
        while (true)
            try {
                x = Long.parseLong(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Введите корректное x");
            }
        System.out.println("Введите координату y");
        float y;
        while (true)
            try {
                y = Float.parseFloat(scanner.nextLine());
                if (y == 0 || y > 597) {
                    System.out.println("Вы ввели неверное значение, повторите попытку, y должен быть < 597");
                    y = scanner.nextFloat();
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Введите корректное y");
            }
        return new Coordinates(x, y);
    }

    private int readEnginePowerFromConsole() {
        System.out.println("Введите значение enginePower");
        int enginePower;
        while (true)
            try {
                enginePower = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Введите корректное enginePower");
            }
        if (enginePower <= 0) {
            System.out.println("значение enginePower должно быть польше нуля, повторите попытку");
            return readEnginePowerFromConsole();
        } else {
            return enginePower;
        }
    }

    private VehicleType readVehicleTypeFromConsole() {
        VehicleType[] types = VehicleType.values();
        System.out.println("Выберите тип транспорта:");

        for (int i = 0; i < types.length; i++) {
            System.out.println(i + " - " + types[i]);
        }

        while (true) {
            System.out.print("Введите номер варианта: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Ввод не должен быть пустым. Попробуйте снова.");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 0 && choice < types.length) {
                    return types[choice];
                } else {
                    System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный формат. Введите число, соответствующее типу транспорта.");
            }
        }
    }

    private FuelType readFuelTypeFromConsole() {
        FuelType[] fuelTypes = FuelType.values();
        System.out.println("Выберите тип топлива:");

        for (int i = 0; i < fuelTypes.length; i++) {
            System.out.println(i + " - " + fuelTypes[i]);
        }

        while (true) {
            System.out.print("Введите номер варианта: ");
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 0 && choice < fuelTypes.length) {
                    return fuelTypes[choice];
                } else {
                    System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный формат ввода. Введите число, соответствующее типу топлива.");
            }
        }
    }
}
