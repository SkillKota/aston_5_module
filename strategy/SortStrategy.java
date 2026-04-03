package homework5.strategy;

import homework5.User;
import homework5.collection.UserList;
import java.util.Comparator;

public interface SortStrategy {
    void sort(UserList users, Comparator<User> comparator);
}
