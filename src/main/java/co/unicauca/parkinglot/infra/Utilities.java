
package co.unicauca.parkinglot.infra;

import java.time.LocalDateTime;

/**
 *
 * @author Rivera
 */
public class Utilities {
    int dias = 0;
    int hour = 0;
    int minute = 0;
    
    
    public Utilities(){
    }
    /**
     * Metodo que asigna la hora, minuto y dias de estancia del vehiculo en el parqueadero.
     * @param input fecha de entrada del vehiculo
     * @param output fecha y hora de salida del vehiculo
     */
    public void calculateTime(LocalDateTime input, LocalDateTime output){
        
        this.hour = Math.abs(((24-(output.getHour()))-1) - (24-(input.getHour())));
        this.minute = output.getMinute() - input.getMinute();
        this.dias = diasCompletos(input, output);
    }
    /**
    * Metodo redondeo, aproxima al valor mas cercano de su centesima
    * @param value tipo long al que se quiere redondear.
    * @return el rodondeo en tipo de dato long
    */
    public long redondeo(long value){
        if((value % 100) != 0){
           return  value + (100-(value % 100));
        }
        return value;
    }
    public int getDias(){
        return this.dias;
    }
    public int getHour() {
        
        return hour;
    }

    public int getMinute() {
        return minute;
    }
    /**
     * diasCompletos fucion que valida los dias segun el horario
     * del medio dia.
     * @param input fecha y hora de ingreso
     * @param  output fecha y hora de salida
     * @return numero de dias
    */
    public int diasCompletos(LocalDateTime input, LocalDateTime output){
        int aux = output.getDayOfMonth() - input.getDayOfMonth();
        if(aux != 0){
            if((Math.abs(24- output.getHour()) <= 12)){
                aux--;
                if(Math.abs(24-input.getHour()) <= 12)
                    aux--;
            }
        }
        this.hour = output.getHour() - input.getHour();
        return aux;
    }
    /**
      * Sorteo Metodo que genera un numero aleatorio de 1 a 1000.
      * @return true si se ha ganado el sorteo o caso contrario false.
    */
    public boolean sorteo(){
        int numberRandom = (int)(Math.random()*1000+1);
        if(numberRandom == 1000){
            return true;
        }
        return false;
    }
    
}
