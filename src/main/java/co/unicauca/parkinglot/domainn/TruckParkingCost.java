/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.domainn;

import co.unicauca.parkinglot.infra.Utilities;
import java.time.LocalDateTime;

/**
 *
 * @author Rivera
 */

/**
 * Metodo implementado en la clase hija que calcula el valor del parqueo de un
 * vehiculo de tipo Truck.
 */
public class TruckParkingCost implements IParkingCost {

    @Override
    public long calculateCost(Vehicle veh, LocalDateTime input, LocalDateTime output) {

        Utilities util = new Utilities();
        util.calculateTime(input, output);
        long result = 15000;
        int horas = util.getHour();

        if (util.getDias() > 0) {
            result = util.getDias() * result;
            if (util.getHour() != 0) {
                result = result + (util.getHour() * 625);

            }
        } else {
            if (util.getHour() <= 12) {
                result = 10000;

            }
        }

        //Llamado metodo sorteo en caso de que el numero generado coincida con el 
        //numero ganador.
        if (util.sorteo()) {
            result = 0;
        }
        return util.redondeo(result);

    }

}
