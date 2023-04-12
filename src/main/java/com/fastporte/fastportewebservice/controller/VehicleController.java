package com.fastporte.fastportewebservice.controller;


import com.fastporte.fastportewebservice.entities.Contract;
import com.fastporte.fastportewebservice.entities.Driver;
import com.fastporte.fastportewebservice.entities.Vehicle;
import com.fastporte.fastportewebservice.service.IDriverService;
import com.fastporte.fastportewebservice.service.IVehicleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    private final IVehicleService vehicleService;
    private final IDriverService driverService;

    public VehicleController(IVehicleService vehicleService, IDriverService driverService) {
        this.vehicleService = vehicleService;
        this.driverService = driverService;
    }

    //Retornar driver por id
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> findVehicleById(@PathVariable("id") Long id) {
        try {
            Optional<Vehicle> vehicle = vehicleService.getById(id);
            if (vehicle.isPresent())
                return new ResponseEntity<>(vehicle.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/find/{category}/{quantity}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vehicle>> findByType_cardQuantityCategory(
            @PathVariable("category") String category,
            @PathVariable("quantity") Long quantity) {
        try {
            List<Vehicle> vehicle = vehicleService.getAll();
            vehicle.removeIf(vehicle_ -> !vehicle_.getCategory().equals(category));
            vehicle.removeIf(vehicle_ -> !vehicle_.getQuantity().equals(quantity));
            if (vehicle.size() > 0)
                return new ResponseEntity<>(vehicle, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Insertar vehicle
    @PostMapping(value = "/{driverId}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Insert Vehicle", notes="Method to insert a vehicle")
    @ApiResponses({
            @ApiResponse(code=201, message="Vehicle created"),
            @ApiResponse(code=404, message="Vehicle not created"),
            @ApiResponse(code=501, message="Vehicle server error")
    })
    public ResponseEntity<Vehicle> insertVehicle(@PathVariable("driverId") Long driverId,
                                                    @Valid @RequestBody Vehicle vehicle) {
        try {
            Optional<Driver> driver = driverService.getById(driverId);
            if (driver.isPresent()) {
                vehicle.setDriver(driver.get());
                Vehicle vehicleNew = vehicleService.save(vehicle);
                return ResponseEntity.status(HttpStatus.CREATED).body(vehicleNew);
            } else
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Actualizar vehicle
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Update Vehicle", notes="Method to update a vehicle")
    @ApiResponses({
            @ApiResponse(code=201, message="Vehicle updated"),
            @ApiResponse(code=404, message="Vehicle not updated"),
            @ApiResponse(code=501, message="Vehicle server error")
    })
    public ResponseEntity<Vehicle> updateClient(@PathVariable("id") Long id,
                                                   @Valid @RequestBody Vehicle vehicle) {
        try {
            Optional<Vehicle> vehicleUpdate = vehicleService.getById(id);
            if (!vehicleUpdate.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            vehicle.setId(id);
            vehicleService.save(vehicle);
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Eliminar vehicle
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Delete Vehicle", notes="Method to delete a vehicle")
    @ApiResponses({
            @ApiResponse(code=201, message="Vehicle deleted"),
            @ApiResponse(code=404, message="Vehicle not deleted"),
            @ApiResponse(code=501, message="Vehicle server error")
    })
    public ResponseEntity<Vehicle> deleteExperience(@PathVariable("id") Long id) {
        try {
            Optional<Vehicle> vehicleDelete = vehicleService.getById(id);
            if (!vehicleDelete.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            vehicleService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
