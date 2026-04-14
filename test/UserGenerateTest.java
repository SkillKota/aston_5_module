package homework5.test;

import homework5.User;
import homework5.collection.UserList;
import homework5.service.RandomUserGenerator;

public class UserGenerateTest {
    public static void main(String[] args) {
        testRandomGeneratorCreatesRequestedNumberOfValidUsers();
        System.out.println("UserGenerateTest: все проверки пройдены");
    }

    private static void testRandomGeneratorCreatesRequestedNumberOfValidUsers() {
        RandomUserGenerator randomUserGenerator = new RandomUserGenerator();

        UserList users = randomUserGenerator.input(10);

        assertEquals(10, users.size());
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            assertTrue(user.getId() > 0, "id должен быть больше 0");
            assertTrue(!user.getName().isBlank(), "name не должен быть пустым");
            assertTrue(user.getEmail().contains("@"), "email должен содержать @");
            assertTrue(user.getEmail().contains("."), "email должен содержать .");
            assertTrue(user.getPassword().length() >= 6, "password должен быть не короче 6 символов");
        }
    }

    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new IllegalStateException("Ожидалось " + expected + ", получено " + actual);
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }
}
