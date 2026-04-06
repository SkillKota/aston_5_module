package homework5.test;

import homework5.User;
import homework5.service.RandomUserGenerator;

import java.util.List;

public class UserGenerateTest {

    public static void main(String[] args) {

        List<User> users = RandomUserGenerator.generateRandomUsers(10);

        users.forEach(user -> {
            System.out.println(user.toString());
        });

    }

}
