package homework5.collection;

import homework5.User;

import java.util.ArrayList;
import java.util.List;

public class UserList implements UserCollection {
    private static final int DEFAULT_CAPACITY = 10;

    private User[] elements;
    private int size;

    public UserList() {
        this(DEFAULT_CAPACITY);
    }

    public UserList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Начальная емкость не может быть отрицательной");
        }

        this.elements = new User[Math.max(initialCapacity, DEFAULT_CAPACITY)];
    }

    @Override
    public void add(User user) {
        validateUser(user);
        ensureCapacity(size + 1);
        elements[size++] = user;
    }

    @Override
    public User get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(int index, User user) {
        checkIndex(index);
        validateUser(user);
        elements[index] = user;
    }

    @Override
    public User remove(int index) {
        checkIndex(index);
        User removed = elements[index];

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[--size] = null;
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public List<User> toList() {
        List<User> users = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            users.add(elements[i]);
        }
        return users;
    }

    @Override
    public String toString() {
        return toList().toString();
    }

    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= elements.length) {
            return;
        }

        int newCapacity = elements.length * 2;
        while (newCapacity < requiredCapacity) {
            newCapacity *= 2;
        }

        User[] newElements = new User[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index);
        }
    }
    //проверка на null
    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Пользователь не может быть null");
        }
    }
}
