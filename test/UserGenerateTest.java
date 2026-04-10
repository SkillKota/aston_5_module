package homework5.test;

import homework5.User;
import homework5.service.RandomUserGenerator;

import java.util.List;

public class UserGenerateTest {

    public static void main(String[] args) {

        RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
        List<User> users = randomUserGenerator.input(10).toList();

        users.forEach(user -> {
            System.out.println(user.toString());
        });

    }

}
