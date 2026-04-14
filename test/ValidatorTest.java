package homework5.test;

import homework5.service.UserValidator;

public class ValidatorTest {
    public static void main(String[] args) {
        testEmailValidation();
        testNameValidation();
        testPasswordValidation();
        testValidateId();
        System.out.println("ValidatorTest: все проверки пройдены");
    }

    private static void testEmailValidation() {
        assertTrue(UserValidator.validateEmail("mail@gmail.com"), "email должен быть валидным");
        assertTrue(UserValidator.validateEmail("mail123@yandex.ru"), "email должен быть валидным");
        assertTrue(UserValidator.validateEmail("mail123@pochta.ru"), "email должен быть валидным");

        assertFalse(UserValidator.validateEmail("mailgmail.com"), "email без @ должен быть невалидным");
        assertFalse(UserValidator.validateEmail("mail@gmailcom"), "email без . должен быть невалидным");
        assertFalse(UserValidator.validateEmail("mail@bk.k"), "email с короткой зоной должен быть невалидным");
        assertFalse(UserValidator.validateEmail(null), "null email должен быть невалидным");
    }

    private static void testNameValidation() {
        assertTrue(UserValidator.validateName("Афанасий"), "имя должно быть валидным");
        assertTrue(UserValidator.validateName("Абдуль'Салахаддим"), "имя должно быть валидным");
        assertTrue(UserValidator.validateName("Dmitry"), "имя должно быть валидным");

        assertFalse(UserValidator.validateName("123sd"), "имя с цифрами должно быть невалидным");
        assertFalse(UserValidator.validateName("!@%^"), "имя со спецсимволами должно быть невалидным");
        assertFalse(UserValidator.validateName("N A M E"), "имя с пробелами должно быть невалидным");
        assertFalse(UserValidator.validateName(null), "null имя должно быть невалидным");
    }

    private static void testPasswordValidation() {
        assertTrue(UserValidator.validatePassword("пароль123"), "пароль должен быть валидным");
        assertTrue(UserValidator.validatePassword("password"), "пароль должен быть валидным");
        assertTrue(UserValidator.validatePassword("qwe!@#ccc"), "пароль должен быть валидным");

        assertFalse(UserValidator.validatePassword("парол"), "короткий пароль должен быть невалидным");
        assertFalse(UserValidator.validatePassword("p"), "короткий пароль должен быть невалидным");
        assertFalse(UserValidator.validatePassword(" "), "короткий пароль должен быть невалидным");
        assertFalse(UserValidator.validatePassword(null), "null пароль должен быть невалидным");
    }

    private static void testValidateId() {
        assertTrue(UserValidator.validateId(1), "id должен быть валидным");
        assertFalse(UserValidator.validateId(-1), "отрицательный id должен быть невалидным");
        assertFalse(UserValidator.validateId(0), "нулевой id должен быть невалидным");
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }

    private static void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new IllegalStateException(message);
        }
    }
}
