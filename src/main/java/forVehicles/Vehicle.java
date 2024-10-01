package forVehicles;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Vehicle implements Comparable<Vehicle> {
    private String name;
    private int id;
    private Integer enginePower;
    private Coordinates coordinates;
    private VehicleType vehicleType;
    private FuelType fuelType;
    private java.time.LocalDate creationDate;
    private static int nextid = 0;

    public Vehicle(String name, Coordinates coordinates, Integer enginePower, VehicleType vehicleType, FuelType fuelType) {
        id = nextid++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = java.time.LocalDate.now();
        this.enginePower = enginePower;
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
    }

    public static void setNextId(int nextid) {
        Vehicle.nextid = nextid;
    }

    @Override
    public String toString() {
        return "Vehicle ID: " + id +
                ", Name: " + name +
                ", Coordinates: " + coordinates +
                ", Creation Date: " + creationDate +
                ", Engine Power: " + enginePower +
                ", Type: " + vehicleType +
                ", Fuel Type: " + fuelType;
    }

    @Override
    public int compareTo(Vehicle other) {
        return Integer.compare(this.enginePower, other.enginePower);
    }
}
