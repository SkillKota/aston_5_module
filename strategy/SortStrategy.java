package homework5.strategy;

import homework5.User;
import java.util.Comparator;
import java.util.List;

public interface SortStrategy {
    void sort(List<User> users, Comparator<User> comparator);
}