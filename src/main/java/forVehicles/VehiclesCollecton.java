package forVehicles;

import lombok.Getter;

import java.util.HashSet;

@Getter
public class VehiclesCollecton {
    private final HashSet<Vehicle> vehicles;
    public VehiclesCollecton(HashSet<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.vehicles.addAll(vehicles);
    }
}