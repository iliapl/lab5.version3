package Utilities;

import forVehicles.Coordinates;

public interface Validate {
    default String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым.");
        }
        return name;
    }

    default Coordinates validateCoordinates(long x, float y) {
        if (y <= 0 || y > 597) {
            throw new IllegalArgumentException("Координата y должна быть > 0 и <= 597.");
        }
        return new Coordinates(x, y);
    }

    default int validateEnginePower(int enginePower) {
        if (enginePower <= 0) {
            throw new IllegalArgumentException("Мощность двигателя должна быть больше 0.");
        }
        return enginePower;
    }
}
