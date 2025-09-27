package dao;

import model.Rental;
import model.User;
import model.Vehicle;
import util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO {

    // Yeni kiralama (Transaction ile)
    public static void save(Rental rental, int userId, int vehicleId) {
        String sql = "INSERT INTO rentals(user_id, vehicle_id, start_date, end_date, total_price, deposit, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false); // transaction başlat

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ps.setInt(2, vehicleId);
                ps.setDate(3, Date.valueOf(rental.getStartDate()));
                ps.setDate(4, Date.valueOf(rental.getEndDate()));
                ps.setDouble(5, rental.getTotalPrice());
                ps.setDouble(6, rental.getDeposit());
                ps.setString(7, rental.getStatus());
                ps.executeUpdate();
            }

            conn.commit(); // her şey başarılıysa commit
            System.out.println("✅ Rental saved to DB");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // hata olursa rollback
                Connection conn = DBUtil.getConnection();
                conn.rollback();
                System.out.println("⚠️ Transaction rolled back");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Tüm kiralamaları listele
    public static List<Rental> findAll() {
        List<Rental> rentals = new ArrayList<>();
        String sql = """
                SELECT r.id, r.start_date, r.end_date, r.total_price, r.deposit, r.status,
                       u.name AS user_name, u.email AS user_email, u.role, u.age,
                       v.type, v.brand, v.model
                FROM rentals r
                JOIN users u ON r.user_id = u.id
                JOIN vehicles v ON r.vehicle_id = v.id
                """;

        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Rental rental = new Rental(
                        new User(
                                rs.getString("user_name"),
                                rs.getString("user_email"),
                                "", // şifre göstermiyoruz
                                rs.getString("role"),
                                rs.getInt("age")
                        ),
                        new Vehicle(rs.getString("brand"), rs.getString("model"),
                                0,0,0,0,0) {
                            @Override
                            public String getType() {
                                return rs.getString("type");
                            }
                        },
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getDouble("total_price"),
                        rs.getDouble("deposit")
                );
                rental.setStatus(rs.getString("status"));
                rentals.add(rental);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }

    // Kiralamayı iptal et
    public static void cancelRental(int rentalId) {
        String sql = "UPDATE rentals SET status='CANCELLED' WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rentalId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Rental cancelled (id=" + rentalId + ")");
            } else {
                System.out.println("❌ Rental not found (id=" + rentalId + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Kiralamayı tamamlama
    public static void completeRental(int rentalId) {
        String sql = "UPDATE rentals SET status='COMPLETED' WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rentalId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Rental completed (id=" + rentalId + ")");
            } else {
                System.out.println("❌ Rental not found (id=" + rentalId + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
