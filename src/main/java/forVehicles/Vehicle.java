package forVehicles;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Vehicle implements Comparable<Vehicle> {
    private String name;
    private int id;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private final Integer enginePower;
    private VehicleType type;
    private FuelType fuelType;
    private static int nextid = 0;

    public Vehicle(String name, Coordinates coordinates, Integer enginePower, VehicleType type, FuelType fuelType) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name не может быть нулевым или пустым");
        }
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates не может быть нулевым или пустым");
        }
        if (enginePower == null || enginePower <= 0) {
            throw new IllegalArgumentException("Engine power должно быть больше 0, но не может быть null");
        }
        if (type == null) {
            throw new IllegalArgumentException("Vehicle не может быть null");
        }
        if (fuelType == null) {
            throw new IllegalArgumentException("Fuel type не может быть null");
        }
        id = nextid++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = java.time.LocalDate.now();
        this.enginePower = enginePower;
        this.type = type;
        this.fuelType = fuelType;
    }
    public static void setNextId(int nextid) {
        Vehicle.nextid = nextid;
    }

    public String vehicleToString() {
        return "Vehicle ID: " + id +
                ", Name: " + name +
                ", Coordinates: " + coordinates +
                ", Creation Date: " + creationDate +
                ", Engine Power: " + enginePower +
                ", Type: " + type +
                ", Fuel Type: " + fuelType;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Vehicle vehicle = (Vehicle) obj;
        return (vehicle.id == this.id);
    }
    @Override
    public int compareTo(Vehicle other) {
        return Integer.compare(this.enginePower, other.enginePower);
    }
}
