package homework5.test;

import homework5.User;
import homework5.service.UserComparator;
import homework5.util.SortField;

import java.util.Comparator;

public class ComparatorTest {
    public static void main(String[] args) {
        UserComparator comparator = new UserComparator();
        User baseUser = createUser(2, "Никита", "222222", "pochta@gmail.com");

        User[] users = {
                createUser(1, "Ярослав", "111111", "Zpochta@gmail.com"),
                createUser(2, "Никита", "222222", "pochta@gmail.com"),
                createUser(3, "Александр", "333333", "Apochta@gmail.com")
        };

        for (User user : users) {
            assertCompare(
                    Integer.compare(user.getId(), baseUser.getId()),
                    comparator.getComparator(SortField.ID),
                    user,
                    baseUser
            );
            assertCompare(
                    user.getName().compareToIgnoreCase(baseUser.getName()),
                    comparator.getComparator(SortField.NAME),
                    user,
                    baseUser
            );
            assertCompare(
                    user.getEmail().compareToIgnoreCase(baseUser.getEmail()),
                    comparator.getComparator(SortField.EMAIL),
                    user,
                    baseUser
            );
            assertCompare(
                    user.getPassword().compareTo(baseUser.getPassword()),
                    comparator.getComparator(SortField.PASSWORD),
                    user,
                    baseUser
            );
        }

        System.out.println("ComparatorTest: все проверки пройдены");
    }

    private static User createUser(int id, String name, String password, String email) {
        return new User.BuilderUser(id)
                .name(name)
                .password(password)
                .email(email)
                .build();
    }

    private static void assertCompare(int expected, Comparator<User> comparator, User first, User second) {
        int actual = comparator.compare(first, second);
        if (Integer.signum(expected) != Integer.signum(actual)) {
            throw new IllegalStateException("Некорректный результат сравнения");
        }
    }
}
