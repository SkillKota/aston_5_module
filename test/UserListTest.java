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

        assertCondition(userList.size() == 2, "Размер должен быть равен 2");
        assertCondition(firstUser.equals(userList.get(0)), "Первый элемент должен совпадать");
        assertCondition(secondUser.equals(userList.get(1)), "Второй элемент должен совпадать");
    }

    private static void testSet() {
        UserList userList = new UserList();
        userList.add(createUser(1, "Ivan", "secret1", "ivan@test.com"));
        User replacement = createUser(5, "Olga", "secret5", "olga@test.com");

        userList.set(0, replacement);

        assertCondition(replacement.equals(userList.get(0)), "Элемент должен быть заменен");
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

        assertCondition(secondUser.equals(removed), "Должен удалиться второй пользователь");
        assertCondition(userList.size() == 2, "Размер должен уменьшиться");
        assertCondition(thirdUser.equals(userList.get(1)), "Элементы должны сдвинуться влево");
    }

    private static void testClear() {
        UserList userList = new UserList();
        userList.add(createUser(1, "Ivan", "secret1", "ivan@test.com"));
        userList.add(createUser(2, "Petr", "secret2", "petr@test.com"));

        userList.clear();

        assertCondition(userList.isEmpty(), "Коллекция должна быть пустой");
        assertCondition(userList.size() == 0, "Размер должен быть равен 0");
    }

    private static User createUser(int id, String name, String password, String email) {
        return new User.BuilderUser(id)
                .name(name)
                .password(password)
                .email(email)
                .build();
    }

    private static void assertCondition(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }
}
