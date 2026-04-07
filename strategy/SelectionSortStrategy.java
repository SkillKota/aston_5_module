package homework5.strategy;

import homework5.User;
import homework5.collection.UserList;

import java.util.Comparator;

public class SelectionSortStrategy extends AbstractSortStrategy {
    @Override
    public void sort(UserList users, Comparator<User> comparator) {
        int n = users.size();

        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (compare(users.get(j), users.get(minIndex), comparator) < 0) {
                    minIndex = j;
                }
            }

            swap(users, i, minIndex);
        }
    }
}
