

package co.unicauca.parkinglot.access;

import co.unicauca.parkinglot.domainn.Vehicle;
import co.unicauca.parkinglot.domain.service.Service;
import co.unicauca.parkinglot.domainn.TypeEnum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rivera
 */
public class VehicleRepository implements IVehicleRepository{
    
    private Connection conn;
    
    VehicleRepository(){
        this.initDataBase();
    }
    
    @Override
    public boolean save(Vehicle newVehicle) {
        try {
            //Validate vehicle
            if (newVehicle == null || newVehicle.getPlate().isEmpty()) {
                return false;
            }
            this.connect();

            String sql = "INSERT INTO Vehicle ( VehiclePlate, VehicleType ) "
                          + "VALUES ( ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newVehicle.getPlate());
            pstmt.setString(2, newVehicle.getType().toString());
            pstmt.executeUpdate();
            this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Vehicle> list() {
        List<Vehicle> vehicles = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Vehicle";
            this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vehicle newVehicle = new Vehicle();
                newVehicle.setPlate(rs.getString("VehiclePlate"));
                String type = rs.getString("VehicleType");
                newVehicle.setType(TypeEnum.valueOf(type));

                vehicles.add(newVehicle);
            }
            this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicles;
    }
    private void initDataBase(){
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Vehicle (\n"
                + "	VehiclePlate text PRIMARY KEY,\n"
                + "	VehicleType text NOT NULL\n"
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void connect(){
         // SQLite connection string
        String url = "jdbc:sqlite:./mydatabase.db";
        //String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void disconnect(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    /**
     * Metodo implementado desde la clase hija que limpia la tabla vehicle
     * @return boolean
     */
    @Override
     public boolean cleanTable() {
        try {
            
            this.connect();

            String sql = "delete from Vehicle";

            PreparedStatement pstmt = conn.prepareStatement(sql);
        
            pstmt.executeUpdate();
            this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
