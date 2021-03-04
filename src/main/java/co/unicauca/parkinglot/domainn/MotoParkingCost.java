/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.parkinglot.domainn;

import java.time.LocalDateTime;
import co.unicauca.parkinglot.infra.Utilities;

/**
 *
 * @author Rivera
 */

/**
 * Metodo implementado EN la clase hija que calcula el valor del parqueo
 * de un vehiculo de tipo Moto.
 */
public class MotoParkingCost implements IParkingCost {

    @Override
    public long calculateCost(Vehicle veh, LocalDateTime input, LocalDateTime output) {
        Utilities util = new Utilities();
        util.calculateTime(input, output);
        long result = 1000;

        if (util.getHour() > 0) {
            result = (util.getHour() + 1) * 500;
            if (util.getMinute() >= 30) {
                result = result + 250;
                if (util.getMinute() >= 45) {
                    result = result + 125;
                }
            } else {
                result = result + 125;
            }
        }

        return util.redondeo(result);
    }

}
