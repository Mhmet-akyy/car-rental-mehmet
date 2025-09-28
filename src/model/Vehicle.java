package model;

public abstract class Vehicle {
    private String brand;
    private String model;
    private double pricePerHour;
    private double pricePerDay;
    private double pricePerWeek;
    private double pricePerMonth;
    private double value; // Araç değeri

    public Vehicle(String brand, String model,
                   double pricePerHour, double pricePerDay, double pricePerWeek, double pricePerMonth,
                   double value) {
        this.brand = brand;
        this.model = model;
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.pricePerWeek = pricePerWeek;
        this.pricePerMonth = pricePerMonth;
        this.value = value;
    }

    public abstract String getType();

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getPricePerHour() { return pricePerHour; }
    public double getPricePerDay() { return pricePerDay; }
    public double getPricePerWeek() { return pricePerWeek; }
    public double getPricePerMonth() { return pricePerMonth; }
    public double getValue() { return value; }

    @Override
    public String toString() {
        return getType() + " - " + brand + " " + model +
                " | Hour: " + pricePerHour +
                " | Day: " + pricePerDay +
                " | Week: " + pricePerWeek +
                " | Month: " + pricePerMonth +
                " | Value: " + value;
    }
}
