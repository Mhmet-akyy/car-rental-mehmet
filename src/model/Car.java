package model;

public class Car extends Vehicle {
    public Car(String brand, String model,
               double hour, double day, double week, double month, double value) {
        super(brand, model, hour, day, week, month, value);
    }

    @Override
    public String getType() { return "Car"; }
}
