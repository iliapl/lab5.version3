package toVehicle;

import java.io.Serializable;
import java.time.LocalDate;

public class Vehicle implements Serializable,Comparable<Vehicle> {
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

    public void setId(int id) {
        this.id = id;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    @Override
    public int hashCode() {
        return id;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Vehicle vehicle = (Vehicle) obj;
        return (vehicle.id == this.id);
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public void update(Vehicle vehicle) {
        name = vehicle.name;
        coordinates = vehicle.coordinates;
        type = vehicle.type;
        fuelType = vehicle.fuelType;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public VehicleType getType() {
        return type;
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
    public int compareTo(Vehicle other) {
        return Integer.compare(this.enginePower, other.enginePower);
    }
}
