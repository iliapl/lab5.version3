package forVehicles;

import lombok.Getter;

import java.time.LocalDate;

public class Vehicle implements Comparable<Vehicle> {
    @Getter
    private String name;
    private int id;
    @Getter
    private Coordinates coordinates;
    @Getter
    private java.time.LocalDate creationDate;
    @Getter
    private final Integer enginePower;
    @Getter
    private VehicleType type;
    @Getter
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

    public void setId(int id) {
        this.id = id;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public long getId() {
        return id;
    }

    public void update(Vehicle vehicle) {
        name = vehicle.name;
        coordinates = vehicle.coordinates;
        type = vehicle.type;
        fuelType = vehicle.fuelType;
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
