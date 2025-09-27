package ui;

import model.User;
import model.Vehicle;
import service.UserService;
import service.VehicleService;
import service.RentalService;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    private static final Scanner sc = new Scanner(System.in);

    public static void showMainMenu() {
        while (true) {
            System.out.println("\n=== CAR RENTAL SYSTEM ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. List Vehicles");
            System.out.println("4. Rent Vehicle");
            System.out.println("5. List Rentals");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> registerMenu();
                case 2 -> loginMenu();
                case 3 -> VehicleService.listVehicles();
                case 4 -> rentMenu();
                case 5 -> RentalService.listRentals();
                case 0 -> {
                    System.out.println("👋 Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    private static void registerMenu() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        System.out.print("Role (ADMIN / CUSTOMER / CORPORATE): ");
        String role = sc.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(sc.nextLine());

        UserService.register(name, email, pass, role, age);
        System.out.println("✅ Registration successful!");
    }

    private static void loginMenu() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        User user = UserService.login(email, pass);
        if (user == null) {
            System.out.println("❌ Login failed!");
            return;
        }

        System.out.println("✅ Welcome, " + user.getName() + " (" + user.getRole() + ")");

        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            adminMenu();
        }
    }

    private static void rentMenu() {
        System.out.print("Your Email: ");
        String email = sc.nextLine();
        System.out.print("Your Password: ");
        String pass = sc.nextLine();

        User user = UserService.login(email, pass);
        if (user == null) {
            System.out.println("❌ Invalid credentials!");
            return;
        }

        VehicleService.listVehicles();
        System.out.print("Enter Vehicle ID to rent: ");
        int vehicleId = Integer.parseInt(sc.nextLine());

        System.out.print("Start Date (YYYY-MM-DD): ");
        LocalDate start = LocalDate.parse(sc.nextLine());
        System.out.print("End Date (YYYY-MM-DD): ");
        LocalDate end = LocalDate.parse(sc.nextLine());

        // Şimdilik DB’den gelen araç listesinden index ile alıyoruz
        Vehicle v = VehicleService.getVehicleById(vehicleId);
        if (v == null) {
            System.out.println("❌ Vehicle not found!");
            return;
        }

        RentalService.rentVehicle(user, v, start, end);
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Update Vehicle");
            System.out.println("3. Remove Vehicle");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Type (Car/Motorcycle/Helicopter): ");
                    String type = sc.nextLine();
                    System.out.print("Brand: ");
                    String brand = sc.nextLine();
                    System.out.print("Model: ");
                    String model = sc.nextLine();
                    System.out.print("Price/Hour: ");
                    double hour = Double.parseDouble(sc.nextLine());
                    System.out.print("Price/Day: ");
                    double day = Double.parseDouble(sc.nextLine());
                    System.out.print("Price/Week: ");
                    double week = Double.parseDouble(sc.nextLine());
                    System.out.print("Price/Month: ");
                    double month = Double.parseDouble(sc.nextLine());
                    System.out.print("Value: ");
                    double value = Double.parseDouble(sc.nextLine());

                    Vehicle v = new Vehicle(type, brand, model, hour, day, week, month, value) {
                        @Override
                        public String getType() {
                            return type;
                        }
                    };
                    VehicleService.addVehicle(v);
                }
                case 2 -> {
                    System.out.print("Enter Vehicle ID to update: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("New Brand: ");
                    String brand = sc.nextLine();
                    System.out.print("New Model: ");
                    String model = sc.nextLine();
                    System.out.print("New Price/Hour: ");
                    double hour = Double.parseDouble(sc.nextLine());
                    System.out.print("New Price/Day: ");
                    double day = Double.parseDouble(sc.nextLine());
                    System.out.print("New Price/Week: ");
                    double week = Double.parseDouble(sc.nextLine());
                    System.out.print("New Price/Month: ");
                    double month = Double.parseDouble(sc.nextLine());
                    System.out.print("New Value: ");
                    double value = Double.parseDouble(sc.nextLine());

                    Vehicle v = new Vehicle("Updated", brand, model, hour, day, week, month, value) {
                        @Override
                        public String getType() {
                            return "Car";
                        }
                    };
                    VehicleService.updateVehicle(id, v);
                }
                case 3 -> {
                    System.out.print("Enter Vehicle ID to remove: ");
                    int id = Integer.parseInt(sc.nextLine());
                    VehicleService.removeVehicle(id);
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }
}
