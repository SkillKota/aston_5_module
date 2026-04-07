package homework5.strategy;

import homework5.User;
import homework5.collection.UserList;

import java.util.Comparator;

public class InsertionSortStrategy extends AbstractSortStrategy {
    @Override
    public void sort(UserList users, Comparator<User> comparator) {
        int n = users.size();

        for (int i = 1; i < n; i++) {

            User key = users.get(i);
            int j = i - 1;

            while (j >= 0 && compare(users.get(j), key, comparator) > 0) {
                users.set(j + 1, users.get(j));
                j--;
            }

            users.set(j + 1, key);
        }
    }
}
