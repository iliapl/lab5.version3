package toVehicle;

import java.util.HashSet;

public class VehiclesCollecton {

    private HashSet<Vehicle> vehicles;

    public VehiclesCollecton(HashSet<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.vehicles.addAll(vehicles);
    }

    public HashSet<Vehicle> getVehicles() {
        return vehicles;
    }
}