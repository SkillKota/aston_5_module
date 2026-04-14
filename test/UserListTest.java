package homework5.test;

import homework5.User;
import homework5.collection.UserList;

public class UserListTest {
    public static void main(String[] args) {
        testAddAndGet();
        testSet();
        testRemove();
        testClear();
        System.out.println("UserListTest: все проверки пройдены");
    }

    private static void testAddAndGet() {
        UserList userList = new UserList();
        User firstUser = createUser(1, "Ivan", "secret1", "ivan@test.com");
        User secondUser = createUser(2, "Petr", "secret2", "petr@test.com");

        userList.add(firstUser);
        userList.add(secondUser);

        assertEquals(2, userList.size(), "Размер должен быть равен 2");
        assertEquals(firstUser, userList.get(0), "Первый элемент должен совпадать");
        assertEquals(secondUser, userList.get(1), "Второй элемент должен совпадать");
    }

    private static void testSet() {
        UserList userList = new UserList();
        userList.add(createUser(1, "Ivan", "secret1", "ivan@test.com"));
        User replacement = createUser(5, "Olga", "secret5", "olga@test.com");

        userList.set(0, replacement);

        assertEquals(replacement, userList.get(0), "Элемент должен быть заменен");
    }

    private static void testRemove() {
        UserList userList = new UserList();
        User firstUser = createUser(1, "Ivan", "secret1", "ivan@test.com");
        User secondUser = createUser(2, "Petr", "secret2", "petr@test.com");
        User thirdUser = createUser(3, "Olga", "secret3", "olga@test.com");

        userList.add(firstUser);
        userList.add(secondUser);
        userList.add(thirdUser);

        User removed = userList.remove(1);

        assertEquals(secondUser, removed, "Должен удалиться второй пользователь");
        assertEquals(2, userList.size(), "Размер должен уменьшиться");
        assertEquals(thirdUser, userList.get(1), "Элементы должны сдвинуться влево");
    }

    private static void testClear() {
        UserList userList = new UserList();
        userList.add(createUser(1, "Ivan", "secret1", "ivan@test.com"));
        userList.add(createUser(2, "Petr", "secret2", "petr@test.com"));

        userList.clear();

        assertTrue(userList.isEmpty(), "Коллекция должна быть пустой");
        assertEquals(0, userList.size(), "Размер должен быть равен 0");
    }

    private static User createUser(int id, String name, String password, String email) {
        return new User.BuilderUser(id)
                .name(name)
                .password(password)
                .email(email)
                .build();
    }

    private static void assertEquals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new IllegalStateException(message);
        }
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new IllegalStateException(message);
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }
}
