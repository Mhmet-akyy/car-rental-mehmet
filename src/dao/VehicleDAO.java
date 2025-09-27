package dao;

import model.*;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    // Araç ekle
    public static void save(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles(type, brand, model, price_per_hour, price_per_day, price_per_week, price_per_month, value) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, vehicle.getType());
            ps.setString(2, vehicle.getBrand());
            ps.setString(3, vehicle.getModel());
            ps.setDouble(4, vehicle.getPricePerHour());
            ps.setDouble(5, vehicle.getPricePerDay());
            ps.setDouble(6, vehicle.getPricePerWeek());
            ps.setDouble(7, vehicle.getPricePerMonth());
            ps.setDouble(8, vehicle.getValue());

            ps.executeUpdate();
            System.out.println("✅ Vehicle saved: " + vehicle.getBrand() + " " + vehicle.getModel());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tüm araçları getir
    public static List<Vehicle> findAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";

        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String type = rs.getString("type");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                double hour = rs.getDouble("price_per_hour");
                double day = rs.getDouble("price_per_day");
                double week = rs.getDouble("price_per_week");
                double month = rs.getDouble("price_per_month");
                double value = rs.getDouble("value");

                Vehicle v = switch (type.toLowerCase()) {
                    case "car" -> new Car(brand, model, hour, day, week, month, value);
                    case "motorcycle" -> new Motorcycle(brand, model, hour, day, week, month, value);
                    case "helicopter" -> new Helicopter(brand, model, hour, day, week, month, value);
                    default -> null;
                };

                if (v != null) vehicles.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    // Araç sil
    public static void deleteById(int id) {
        String sql = "DELETE FROM vehicles WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("🗑️ Vehicle deleted (id=" + id + ")");
            } else {
                System.out.println("❌ Vehicle not found (id=" + id + ")");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Araç güncelle
    public static void update(int id, Vehicle vehicle) {
        String sql = "UPDATE vehicles SET type=?, brand=?, model=?, price_per_hour=?, price_per_day=?, price_per_week=?, price_per_month=?, value=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, vehicle.getType());
            ps.setString(2, vehicle.getBrand());
            ps.setString(3, vehicle.getModel());
            ps.setDouble(4, vehicle.getPricePerHour());
            ps.setDouble(5, vehicle.getPricePerDay());
            ps.setDouble(6, vehicle.getPricePerWeek());
            ps.setDouble(7, vehicle.getPricePerMonth());
            ps.setDouble(8, vehicle.getValue());
            ps.setInt(9, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Vehicle updated (id=" + id + ")");
            } else {
                System.out.println("❌ Vehicle not found (id=" + id + ")");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
