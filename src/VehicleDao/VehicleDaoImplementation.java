package VehicleDao;

import Database.Database;
import Vehicle.Vehicle;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDaoImplementation implements VehicleDao {

    //Database Connection for DAO
    private static Connection conn = Database.getConn();

    @Override
    public void addVehicle(Vehicle vehicle) throws SQLException {

        //Create SQL Query
        String query = "Insert into vehicles(id, year, make, model) VALUES (" + vehicle.getId() + ", " + vehicle.getYear()+ ",?,?)";

        //Prepare SQL Query
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, vehicle.getMake());
        ps.setString(2, vehicle.getModel());

        //Execute SQL Query
        int n = ps.executeUpdate();
    }

    @Override
    public void deleteVehicle(int id) throws SQLException {
        //Create SQL Query
        String query = "DELETE FROM Vehicles where id = " + id + "";

        //Prepare SQL Query
        PreparedStatement ps = conn.prepareStatement(query);

        //Execute SQL Query
        ps.executeUpdate();
    }

    @Override
    public Vehicle getVehicle(int id) throws SQLException {
        //Create SQL Query
        String query = "SELECT * FROM Vehicles WHERE id = "+id+"";

        //Prepare SQL Query
        PreparedStatement ps = conn.prepareStatement(query);

        //Execute SQL Query
        ResultSet rs = ps.executeQuery();

        Vehicle vehicle = new Vehicle();
        boolean check = false;
        while(rs.next()){
            check = true;
            vehicle.setId(rs.getInt("id"));
            vehicle.setYear(rs.getInt("year"));
            vehicle.setMake(rs.getString("make"));
            vehicle.setModel(rs.getString("model"));
        }

        if(check) { return vehicle; }
        else{ return null; }
    }

    @Override
    public List<Vehicle> getVehicles() throws SQLException {
        //Create SQL Query
        String query = "SELECT * FROM Vehicles";

        //Prepare SQL Query
        PreparedStatement ps = conn.prepareStatement(query);

        //Execute SQL Query
        ResultSet rs = ps.executeQuery();

        List<Vehicle> vList = new ArrayList();
        while(rs.next()){
            Vehicle vehicle = new Vehicle();
            vehicle.setId(rs.getInt("id"));
            vehicle.setYear(rs.getInt("year"));
            vehicle.setMake(rs.getString("make"));
            vehicle.setModel(rs.getString("model"));
            vList.add(vehicle);
        }
        return vList;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) throws SQLException {
        //Create SQL Query
        String query = "UPDATE Vehicles SET year=" + vehicle.getYear() + ", make=?, model=? WHERE id=" + vehicle.getId() +"";

        //Prepare SQL Query
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, vehicle.getMake());
        ps.setString(2, vehicle.getModel());

        //Execute SQL Query
        ps.executeUpdate();
    }
}
