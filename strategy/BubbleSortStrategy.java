package homework5.strategy;

import homework5.User;
import homework5.collection.UserList;

import java.util.Comparator;

public class BubbleSortStrategy extends AbstractSortStrategy {

    @Override
    public void sort(UserList users, Comparator<User> comparator) {
        int n = users.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                User a = users.get(j);
                User b = users.get(j + 1);

                if (compare(a, b, comparator) > 0) {
                    swap(users, j, j + 1);
                }
            }
        }
    }
}
