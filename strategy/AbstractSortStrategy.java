package homework5.strategy;

import homework5.User;
import homework5.collection.UserList;

import java.util.Comparator;

public abstract class AbstractSortStrategy implements SortStrategy {

    protected SortMode mode = SortMode.NORMAL;

    protected void swap(UserList list, int i, int j) {
        User temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public void setMode(SortMode mode) {
        this.mode = mode;
    }

    protected int compare(User u1, User u2, Comparator<User> baseComparator) {

        if (mode == SortMode.EVEN_ODD) {
            boolean even1 = u1.getId() % 2 == 0;
            boolean even2 = u2.getId() % 2 == 0;

            if (even1 && !even2) return -1;
            if (!even1 && even2) return 1;
        }

        return baseComparator.compare(u1, u2);
    }

}
