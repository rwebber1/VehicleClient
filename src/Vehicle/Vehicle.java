package Vehicle;

public class Vehicle {
    //Member Variables
    public int Id;
    int Year;
    String Make;
    String Model;

    //Constructor
    public Vehicle (int id, int year,String make, String model){
        Id = id;
        Year = year;
        Make = make;
        Model = model;
    }

    //Accessors
    public int getId() { return Id; }
    public int getYear() { return Year; }
    public String getMake() { return Make; }
    public String getModel() { return Model; }

    //Mutators
    public void setId(int id) { Id = id; }
    public void setYear(int year) { Year = year; }
    public void setMake(String make) { Make = make; }
    public void setModel(String model) { Model = model; }
}
