package homework5.collection;

import homework5.User;
import java.util.List;

public interface UserCollection {
    void add(User user);

    User get(int index);

    void set(int index, User user);

    User remove(int index);

    int size();

    boolean isEmpty();

    void clear();

    List<User> toList();
}
