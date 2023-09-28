package pl.hodan.vehicles.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.hodan.vehicles.model.Vehicle;
import pl.hodan.vehicles.service.VehiclesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("vehicle")
public class VehiclesApi {
    private final VehiclesService vehiclesService;


    public VehiclesApi(VehiclesService vehicleService) {
        this.vehiclesService = vehicleService;
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,})
    public List<Vehicle> getVehicles() {
        return vehiclesService.getVehicles();
    }

    @GetMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,})
    public ResponseEntity<Vehicle> getVehiclesById(@PathVariable long id) {
        Optional<Vehicle> searchedVehicle = vehiclesService.getVehiclesById(id);

        if (searchedVehicle.isPresent())
            return new ResponseEntity<>(searchedVehicle.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/color", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,})
    public ResponseEntity<List<Vehicle>> getVehiclesColorById(@RequestParam(required = true) String color) {
        List<Vehicle> searchedVehicle = vehiclesService.getVehiclesByColor(color);

        if (searchedVehicle != null && !searchedVehicle.isEmpty())
            return new ResponseEntity<>(searchedVehicle, HttpStatus.OK);
        return ResponseEntity.ok(searchedVehicle);

    }

    @PostMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,})
    public ResponseEntity addVehicle(@Validated @RequestBody Vehicle vehicle) {
        if (vehiclesService.addVehicle(vehicle))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,})
    public ResponseEntity modificationVehicle(@Validated @RequestBody Vehicle vehicle) {
        if (vehiclesService.modificationVehicle(vehicle))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,})
    public ResponseEntity modificationColorById(@PathVariable long id, @RequestParam @NotNull String color) {
        if (vehiclesService.modificationColor(id, color))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,})
    public ResponseEntity removeVehicle(@PathVariable long id) {
        if (vehiclesService.removeVehicle(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
