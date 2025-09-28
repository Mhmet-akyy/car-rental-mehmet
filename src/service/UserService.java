package service;

import dao.UserDAO;
import model.User;
import util.PasswordUtil;

public class UserService {

    // Kullanıcı kayıt
    public static void register(String name, String email, String password, String role, int age) {
        String hashedPass = PasswordUtil.hashPassword(password);
        User user = new User(name, email, hashedPass, role, age);
        UserDAO.save(user);
    }

    // Giriş (login)
    public static User login(String email, String password) {
        User user = UserDAO.findByEmail(email);
        if (user == null) {
            return null;
        }
        String hashedInput = PasswordUtil.hashPassword(password);
        if (user.getPasswordHash().equals(hashedInput)) {
            return user;
        }
        return null;
    }

    // Kullanıcıları listele (debug için)
    public static void listUsers() {
        UserDAO.listAll();
    }
}
