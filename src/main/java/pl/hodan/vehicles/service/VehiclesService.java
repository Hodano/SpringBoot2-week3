package pl.hodan.vehicles.service;

import org.springframework.stereotype.Service;
import pl.hodan.vehicles.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehiclesService {
    private List<Vehicle> vehicleList;

    public VehiclesService() {
        vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(1, "peuqeot", "206", "red"));
        vehicleList.add(new Vehicle(2, "skoda", "fabia", "green"));
        vehicleList.add(new Vehicle(3, "seat", "leon 2", "black"));
    }

    public List<Vehicle> getVehicles() {
        return vehicleList;
    }

    public Optional<Vehicle> getVehiclesById(long id) {

        return vehicleList.stream().filter(vehicle -> vehicle.getId() == id).findFirst();
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return vehicleList.stream().
                filter(vehicle -> vehicle.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public boolean addVehicle(Vehicle vehicle) {
        boolean idExist = vehicleList.stream()
                .anyMatch(vehilcee -> vehilcee.getId() == vehicle.getId());
        if (!idExist && vehicleList.add(vehicle))
            return true;
        else return false;
    }

    public boolean modificationVehicle(Vehicle newVehicle) {
        Optional<Vehicle> searchedVehicle = getVehiclesById(newVehicle.getId());
        return searchedVehicle.map(vehicle -> {
            vehicleList.remove(vehicle);
            vehicleList.add(newVehicle);
            return true;
        }).orElse(false);
    }

    public boolean modificationColor(long id, String color) {
        Optional<Vehicle> searchedVehicle = getVehiclesById(id);
        return searchedVehicle.map(vehicle -> {
            vehicle.setColor(color);
            return true;
        }).orElse(false);
    }

    public boolean removeVehicle(long id) {
        Optional<Vehicle> searchedVehicle = vehicleList.stream()
                .filter(vehicle -> vehicle.getId() == id)
                .findFirst();
        return searchedVehicle.map(vehicle -> {
            vehicleList.remove(vehicle);
            return true;
        }).orElse(false);
    }
}
