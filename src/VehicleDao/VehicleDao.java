package VehicleDao;

import Vehicle.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDao {
     int addVehicle(Vehicle vehicle) throws SQLException;
     void deleteVehicle(int id) throws SQLException;
     Vehicle getVehicle(int id) throws SQLException;
     List<Vehicle> getVehicles() throws SQLException;
     void updateVehicle(Vehicle vehicle) throws SQLException;
}
