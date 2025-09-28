package dao;

import model.User;
import util.DBUtil;

import java.sql.*;

public class UserDAO {

    // Kullanıcı ekle
    public static void save(User user) {
        String sql = "INSERT INTO users(name, email, password_hash, role, age) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            ps.setString(4, user.getRole());
            ps.setInt(5, user.getAge());

            ps.executeUpdate();
            System.out.println("✅ User saved: " + user.getEmail());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Email ile kullanıcı bul
    public static User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getString("role"),
                        rs.getInt("age")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Tüm kullanıcıları listele
    public static void listAll() {
        String sql = "SELECT * FROM users";
        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n=== Users in DB ===");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("email") + " | " +
                        rs.getString("role") + " | " +
                        rs.getInt("age"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
