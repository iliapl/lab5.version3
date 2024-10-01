package forVehicles;

import lombok.Getter;

import java.util.HashSet;

@Getter
public class VehiclesCollection {
    private final HashSet<Vehicle> vehicles;

    public VehiclesCollection(HashSet<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.vehicles.addAll(vehicles);
    }
}