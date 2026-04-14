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
        return getComparator(baseComparator).compare(u1, u2);
    }

    protected int[] getEvenIndexes(UserList users) {
        int count = 0;
        for (int i = 0; i < users.size(); i++) {
            if (isEven(users.get(i))) {
                count++;
            }
        }

        int[] indexes = new int[count];
        int index = 0;
        for (int i = 0; i < users.size(); i++) {
            if (isEven(users.get(i))) {
                indexes[index++] = i;
            }
        }
        return indexes;
    }

    protected boolean isEvenOddMode() {
        return mode == SortMode.EVEN_ODD;
    }

    private Comparator<User> getComparator(Comparator<User> baseComparator) {
        if (mode == SortMode.EVEN_ODD) {
            return Comparator.comparingInt(User::getId);
        }

        return baseComparator;
    }

    private boolean isEven(User user) {
        return user.getId() % 2 == 0;
    }
}
