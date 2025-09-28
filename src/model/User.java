package model;

public class User {
    private String name;
    private String email;
    private String passwordHash;
    private String role; // ADMIN, CORPORATE, INDIVIDUAL
    private int age;

    public User(String name, String email, String passwordHash, String role, int age) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.age = age;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setRole(String role) { this.role = role; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email +
                "', role='" + role + "', age=" + age + "}";
    }
}
