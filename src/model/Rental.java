package model;

import java.time.LocalDate;

public class Rental {
    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_CANCELLED = "CANCELLED";
    public static final String STATUS_COMPLETED = "COMPLETED";

    private User user;
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
    private double deposit;
    private String status;

    public Rental(User user, Vehicle vehicle, LocalDate startDate, LocalDate endDate, double totalPrice, double deposit) {
        this.user = user;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.deposit = deposit;
        this.status = STATUS_ACTIVE;
    }

    public User getUser() { return user; }
    public Vehicle getVehicle() { return vehicle; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public double getTotalPrice() { return totalPrice; }
    public double getDeposit() { return deposit; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return user.getName() + " rented " + vehicle.getBrand() + " " + vehicle.getModel() +
                " from " + startDate + " to " + endDate +
                " | Total: " + totalPrice +
                " | Deposit: " + deposit +
                " | Status: " + status;
    }
}
