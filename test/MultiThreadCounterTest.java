package homework5.test;

import homework5.User;
import homework5.collection.UserList;
import homework5.service.MultiThreadCounter;

public class MultiThreadCounterTest {
    public static void main(String[] args) {
        testCountById();
        testCountByIdInEmptyCollection();
        System.out.println("MultiThreadCounterTest: все проверки пройдены");
    }

    private static void testCountById() {
        UserList userList = new UserList();
        userList.add(createUser(1));
        userList.add(createUser(2));
        userList.add(createUser(1));
        userList.add(createUser(3));
        userList.add(createUser(1));
        userList.add(createUser(4));

        MultiThreadCounter multiThreadCounter = new MultiThreadCounter();

        assertEquals(3, multiThreadCounter.countById(userList, 1));
        assertEquals(0, multiThreadCounter.countById(userList, 10));
    }

    private static void testCountByIdInEmptyCollection() {
        MultiThreadCounter multiThreadCounter = new MultiThreadCounter();

        assertEquals(0, multiThreadCounter.countById(new UserList(), 1));
    }

    private static User createUser(int id) {
        return new User.BuilderUser(id)
                .name("User" + id)
                .email("user" + id + "@mail.ru")
                .password("password" + id)
                .build();
    }

    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new IllegalStateException("Ожидалось " + expected + ", получено " + actual);
        }
    }
}
