package homework5.test;

import homework5.service.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ValidatorTest {
    @ParameterizedTest
    @CsvSource({
            "mail@gmail.com, mailgmail.com",
            "mail123@yandex.ru, mail@gmailcom",
            "mail123@pochta.ru, mail@bk.k",
    })
    void testEmailValidation (String correctEmail, String incorrectEmail) {
        Assertions.assertTrue(UserValidator.validateEmail(correctEmail));
        Assertions.assertFalse(UserValidator.validateEmail(incorrectEmail));
    }

    @ParameterizedTest
    @CsvSource({
            "Афанасий, 123sd",
            "Абдуль'Салахаддим, !@%^",
            "Dmitry, N A M E",
    })
    void testNameValidation (String correctName, String incorrectName) {
        Assertions.assertTrue(UserValidator.validateName(correctName));
        Assertions.assertFalse(UserValidator.validateName(incorrectName));
    }

    @ParameterizedTest
    @ValueSource(strings = { "пароль123", "password", "qwe!@#ccc", })
    void testValidateNormalPassword (String password) {
        Assertions.assertTrue(UserValidator.validatePassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = { "парол", "p", " ", })
    void testValidateShortPassword (String password) {
        Assertions.assertFalse(UserValidator.validatePassword(password));
    }

    @ParameterizedTest
    @NullSource
    void testValidateNullPassword (String password) {
        Assertions.assertFalse(UserValidator.validatePassword(password));
    }

    @Test
    void testValidateId () {
        int validId = 1;
        int invalidId = -1;
        int zeroId = 0;

        Assertions.assertTrue(UserValidator.validateId(validId));
        Assertions.assertFalse(UserValidator.validateId(invalidId));
        Assertions.assertFalse(UserValidator.validateId(zeroId));
    }
}
