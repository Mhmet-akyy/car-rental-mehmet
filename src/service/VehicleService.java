package service;

import dao.VehicleDAO;
import model.Vehicle;

import java.util.List;

public class VehicleService {

    // Araç ekle
    public static void addVehicle(Vehicle vehicle) {
        VehicleDAO.save(vehicle);
    }

    // Araçları listele
    public static void listVehicles() {
        List<Vehicle> vehicles = VehicleDAO.findAll();
        if (vehicles.isEmpty()) {
            System.out.println("❌ No vehicles found in DB.");
        } else {
            System.out.println("\n=== Vehicle List ===");
            int index = 0;
            for (Vehicle v : vehicles) {
                System.out.println((index++) + " | " + v);
            }
        }
    }

    // Araç sil
    public static void removeVehicle(int id) {
        VehicleDAO.deleteById(id);
    }

    // Araç güncelle
    public static void updateVehicle(int id, Vehicle vehicle) {
        VehicleDAO.update(id, vehicle);
    }
}
