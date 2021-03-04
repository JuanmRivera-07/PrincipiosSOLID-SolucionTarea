/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.domainn;

import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author Rivera
 */
public class ParkingCostFactory {
    private Map<TypeEnum, IParkingCost> dictionary;
    //Singleton
    private static ParkingCostFactory instance;
    
   
    
    private ParkingCostFactory(){
        dictionary = new EnumMap<>(TypeEnum.class);
        dictionary.put(TypeEnum.MOTO, new MotoParkingCost());
        dictionary.put(TypeEnum.CAR, new CarParkingCost());
        dictionary.put(TypeEnum.TRUCK, new TruckParkingCost());
        // Si se requie otro vehiculo, se abre un registro en este diccionario
        // No se viola el principio porque este este modulo no está pensado
        // para que sea estable.
    }
   
        
    public static ParkingCostFactory getInstance() {
        if (instance == null) {
            instance = new ParkingCostFactory();
        }
        return instance;
    }
    
    public IParkingCost getParkingCostVeh(TypeEnum veh) {
        IParkingCost result = null;
      
        if (dictionary.containsKey(veh)) {
            result = dictionary.get(veh);
        }

        return result;
        
    }
   
    
}
