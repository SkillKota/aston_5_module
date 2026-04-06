package homework5.service;

public class UserValidator {
    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean validateName(String name) {
        if (name != null && name.matches("^[A-Za-z]$")) {
            return true;
        }

        return false;
    }

    public static boolean validatePassword(String password) {
        if (password != null && password.length() >= 6) {
            return true;
        }

        return false;
    }

    public static boolean validateId(int id) {
        if (id > 0) {
            return true;
        }

        return false;
    }
}