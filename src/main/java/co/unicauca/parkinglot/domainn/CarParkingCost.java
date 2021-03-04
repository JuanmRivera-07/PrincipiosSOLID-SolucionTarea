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
 * vehiculo de tipo Car.
 */
public class CarParkingCost implements IParkingCost {

    @Override
    public long calculateCost(Vehicle veh, LocalDateTime input, LocalDateTime output) {
        Utilities util = new Utilities();
        util.calculateTime(input, output);
        long result = 2000;
        int horas = util.getHour();

        if (horas >= 2) {
            result = result + (horas * 500);
            if (util.getMinute() > 30) {
                result = result + 250;
                if (util.getMinute() >= 45) {
                    result = result + 125;
                }
            } else {
                result = result + 125;
            }
        } else if (horas <= 1 && horas != 0) {
            if (util.getMinute() >= 30) {
                result = result + 500;
                if (util.getMinute() >= 45) {
                    result = result + 250;
                }
            }

        }

        return util.redondeo(result);

    }

}
