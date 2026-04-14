package homework5.test;

import homework5.User;
import homework5.collection.UserList;
import homework5.strategy.AbstractSortStrategy;
import homework5.strategy.BubbleSortStrategy;
import homework5.strategy.InsertionSortStrategy;
import homework5.strategy.SelectionSortStrategy;
import homework5.strategy.SortMode;

import java.util.Comparator;

public class EvenOddSortTest {
    public static void main(String[] args) {
        testEvenOddSort(new BubbleSortStrategy());
        testEvenOddSort(new SelectionSortStrategy());
        testEvenOddSort(new InsertionSortStrategy());
        System.out.println("EvenOddSortTest: все проверки пройдены");
    }

    private static void testEvenOddSort(AbstractSortStrategy sortStrategy) {
        UserList users = new UserList();
        users.add(createUser(5));
        users.add(createUser(8));
        users.add(createUser(3));
        users.add(createUser(2));
        users.add(createUser(7));
        users.add(createUser(4));

        sortStrategy.setMode(SortMode.EVEN_ODD);
        sortStrategy.sort(users, Comparator.comparingInt(User::getId));

        assertId(users, 0, 5);
        assertId(users, 1, 2);
        assertId(users, 2, 3);
        assertId(users, 3, 4);
        assertId(users, 4, 7);
        assertId(users, 5, 8);
    }

    private static User createUser(int id) {
        return new User.BuilderUser(id)
                .name("User" + id)
                .password("password" + id)
                .email("user" + id + "@mail.ru")
                .build();
    }

    private static void assertId(UserList users, int index, int expectedId) {
        int actualId = users.get(index).getId();
        if (actualId != expectedId) {
            throw new IllegalStateException("Ожидался id " + expectedId + ", получен " + actualId);
        }
    }
}
