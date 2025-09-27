package model;

public class Helicopter extends Vehicle {
    public Helicopter(String brand, String model,
                      double hour, double day, double week, double month, double value) {
        super(brand, model, hour, day, week, month, value);
    }

    @Override
    public String getType() { return "Helicopter"; }
}
