package model;

public class Motorcycle extends Vehicle {
    public Motorcycle(String brand, String model,
                      double hour, double day, double week, double month, double value) {
        super(brand, model, hour, day, week, month, value);
    }

    @Override
    public String getType() { return "Motorcycle"; }
}
