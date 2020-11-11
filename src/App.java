import Database.Database;
import GUI.VehicleClient;
import Vehicle.Vehicle;
import VehicleDao.VehicleDao;
import VehicleDao.VehicleDaoImplementation;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {

        JFrame main = new JFrame("Vehicle Client");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        main.getContentPane().add(new VehicleClient());
        main.setSize(600,800);
        main.pack();
        main.setVisible(true);
    }
}
