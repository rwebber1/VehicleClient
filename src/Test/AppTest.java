package Test;

import Vehicle.Vehicle;
import VehicleDao.VehicleDaoImplementation;
import org.junit.Assert;
import org.junit.Test;
import java.sql.SQLException;

public class AppTest {

    //Tests Add and Get functions for database
    @Test
    public void testInsertAndGet() throws SQLException {
        VehicleDaoImplementation vDao = new VehicleDaoImplementation();
        Vehicle testVehicle = new Vehicle(101010, 2001, "Ford", "Focus");

        vDao.addVehicle(testVehicle);
        Vehicle insertedVehicle = vDao.getVehicle(101010);

        Assert.assertEquals(testVehicle.getId(), insertedVehicle.getId());
        Assert.assertEquals(testVehicle.getYear(), insertedVehicle.getYear());
        Assert.assertEquals(testVehicle.getMake(), insertedVehicle.getMake());
        Assert.assertEquals(testVehicle.getModel(), insertedVehicle.getModel());

        vDao.deleteVehicle(101010);
    }

    //Tests Update Function
    @Test
    public void testUpdate() throws SQLException {
        VehicleDaoImplementation vDao = new VehicleDaoImplementation();
        Vehicle testVehicle = new Vehicle(202020, 1945, "Mazda", "Miata");
        Vehicle updatedVehicle = new Vehicle(202020, 2015, "Mazda", "CX-5");

        vDao.addVehicle(testVehicle);
        vDao.updateVehicle(updatedVehicle);

        Vehicle retrieveVehicle = vDao.getVehicle(202020);

        Assert.assertNotEquals(testVehicle.getYear(),retrieveVehicle.getYear());
        Assert.assertNotEquals(testVehicle.getModel(),retrieveVehicle.getModel());

        vDao.deleteVehicle(202020);
    }

    //Test delete function for database assuming previous test passed
    @Test
    public void testDelete() throws SQLException {
        VehicleDaoImplementation vDao = new VehicleDaoImplementation();
        Vehicle toBeDeleted = new Vehicle(303030, 2000, "BMW", "M3");

        vDao.addVehicle(toBeDeleted);
        vDao.deleteVehicle(303030);

        Assert.assertNull(vDao.getVehicle(303030));
    }
}