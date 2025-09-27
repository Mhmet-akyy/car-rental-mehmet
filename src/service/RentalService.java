package service;

import dao.RentalDAO;
import model.Rental;
import model.User;
import model.Vehicle;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class RentalService {

    // Araç kiralama
    public static void rentVehicle(User user, Vehicle vehicle, LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            System.out.println("❌ Start date must be before end date.");
            return;
        }

        long days = ChronoUnit.DAYS.between(start, end) + 1;
        double price = calculatePrice(vehicle, days);

        // 1) Kurumsal hesaplar için en az 30 gün
        if ("CORPORATE".equalsIgnoreCase(user.getRole())) {
            if (days < 30) {
                System.out.println("❌ Corporate accounts must rent at least 30 days.");
                return;
            }
        }

        // 2) Yüksek değerli araç kuralı
        double deposit = 0.0;
        if (vehicle.getValue() > 2_000_000) {
            if (user.getAge() < 30) {
                System.out.println("❌ You must be at least 30 years old to rent this high-value vehicle.");
                return;
            }
            deposit = price * 0.10;
            System.out.println("⚠️ Deposit required: " + deposit);
        }

        Rental rental = new Rental(user, vehicle, start, end, price, deposit);
        RentalDAO.save(rental, user.getId(), vehicle.getId()); // DB’ye kaydet (Transaction ile)
        System.out.println("✅ Rental successful! Total = " + price + " | Deposit = " + deposit);
    }

    // Fiyat hesaplama
    private static double calculatePrice(Vehicle vehicle, long days) {
        double price;
        if (days < 7) {
            price = days * vehicle.getPricePerDay();
        } else if (days < 30) {
            long weeks = days / 7;
            long extraDays = days % 7;
            price = weeks * vehicle.getPricePerWeek() + extraDays * vehicle.getPricePerDay();
        } else {
            long months = days / 30;
            long remainingDays = days % 30;
            price = months * vehicle.getPricePerMonth() + remainingDays * vehicle.getPricePerDay();
        }
        return price;
    }

    // Tüm kiralamaları listele
    public static void listRentals() {
        List<Rental> rentals = RentalDAO.findAll();
        if (rentals.isEmpty()) {
            System.out.println("❌ No rentals found.");
        } else {
            System.out.println("\n=== Rentals ===");
            for (Rental r : rentals) {
                System.out.println(r);
            }
        }
    }

    // Kiralama iptal
    public static void cancelRental(int rentalId) {
        RentalDAO.cancelRental(rentalId);
    }

    // Kiralama tamamlama
    public static void completeRental(int rentalId) {
        RentalDAO.completeRental(rentalId);
    }
}
