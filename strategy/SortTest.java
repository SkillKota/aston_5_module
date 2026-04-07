package homework5.strategy.test;

import homework5.User;
import homework5.collection.UserList;
import homework5.strategy.*;

import java.util.Comparator;

public class SortTest {

    public static void main(String[] args) {

        UserList users = new UserList();
        users.add(new User.BuilderUser(5).name("Alice").password("pass123").email("alice@mail.com").build());
        users.add(new User.BuilderUser(2).name("Bob").password("123456").email("bob@mail.com").build());
        users.add(new User.BuilderUser(7).name("Charlie").password("abcdef").email("charlie@mail.com").build());
        users.add(new User.BuilderUser(4).name("Diana").password("qwerty").email("diana@mail.com").build());

        Comparator<User> byId = Comparator.comparingInt(User::getId);
        Comparator<User> byName = Comparator.comparing(User::getName);
        Comparator<User> byEmail = Comparator.comparing(User::getEmail);
        Comparator<User> byPassword = Comparator.comparing(User::getPassword);

        AbstractSortStrategy[] strategies = {
                new BubbleSortStrategy(),
                new SelectionSortStrategy(),
                new InsertionSortStrategy()
        };

        String[] strategyNames = {"BubbleSort", "SelectionSort", "InsertionSort"};
        Comparator<User>[] comparators = new Comparator[]{byId, byName, byEmail, byPassword};
        String[] comparatorNames = {"ID", "Name", "Email", "Password"};

        for (int s = 0; s < strategies.length; s++) {
            for (int c = 0; c < comparators.length; c++) {
                for (SortMode mode : SortMode.values()) {

                    UserList copy = copyUserList(users);

                    strategies[s].setMode(mode);
                    strategies[s].sort(copy, comparators[c]);

                    System.out.println("\n=== " + strategyNames[s] + " | Field: " + comparatorNames[c] + " | Mode: " + mode + " ===");
                    System.out.println(copy);
                }
            }
        }
    }

    private static UserList copyUserList(UserList original) {
        UserList copy = new UserList();
        for (User user : original.toList()) {
            copy.add(new User.BuilderUser(user.getId())
                    .name(user.getName())
                    .password(user.getPassword())
                    .email(user.getEmail())
                    .build());
        }
        return copy;
    }
}