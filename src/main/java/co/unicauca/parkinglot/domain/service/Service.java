/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.domain.service;

import co.unicauca.parkinglot.access.IVehicleRepository;
import co.unicauca.parkinglot.domainn.IParkingCost;
import co.unicauca.parkinglot.domainn.ParkingCostFactory;
import co.unicauca.parkinglot.domainn.Vehicle;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rivera
 */
public class Service {
    private IVehicleRepository repository;
    
    public Service(IVehicleRepository repository){
        this.repository = repository;
    }
    public long calculateParkingCost(Vehicle vehicle, LocalDateTime input, LocalDateTime output){
         //Validate vehicle.
        if (vehicle == null) {
            return -1;
        }        
        IParkingCost delivery = ParkingCostFactory.getInstance().getParkingCostVeh(vehicle.getType());
        long result = delivery.calculateCost(vehicle, input, output);  
        
        return result;
    }
    public boolean saveVehicle(Vehicle newVehicle){
        //Validate Vehicle
        if(newVehicle == null || newVehicle.getPlate().isEmpty()) {
            return false;
        }

        repository.save(newVehicle);
        return true;
    }
    public List<Vehicle> listVehicles(){
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles = repository.list();;

        return vehicles;
    }
    public boolean cleanVehicle(){
        
        return repository.cleanTable();
    }
}
