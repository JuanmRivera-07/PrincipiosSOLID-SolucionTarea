/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.access;

import co.unicauca.parkinglot.domainn.Vehicle;
import java.util.List;

/**
 *
 * @author Rivera
 */
public interface IVehicleRepository {
   public boolean save(Vehicle newVehicle);
   public List<Vehicle> list();
   /**
    * Metodo que limpiaa la base de datos
    * @return boolean
    */
   public boolean cleanTable();
}
