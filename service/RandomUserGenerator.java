package homework5.service;

import homework5.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class RandomUserGenerator {

    public static List<User> generateRandomUsers(int size) {
        Random random = new Random();

        return Stream.generate(() -> new User.BuilderUser(Math.abs(random.nextInt()))
                .name(generateString(5, 10, random))
                .password(generateString(6, 20, random))
                .email(generateString(5, 15, random) + "@mail.ru")
                .build()).limit(size).toList();
    }

    private static String generateString(int minLength, int maxLength, Random random) {
        return random.ints(97, 122).limit(random.nextInt(minLength,maxLength))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
