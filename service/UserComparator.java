package homework5.service;

import homework5.User;
import homework5.util.SortField;

import java.util.Comparator;

public class UserComparator implements UserComparatorProvider {
    @Override
    public Comparator<User> getComparator(SortField sortField) {
        return switch (sortField.getMenuNumber()) {
            case 1 -> Comparator.comparingInt(User::getId);
            case 2 -> Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER);
            case 3 -> Comparator.comparing(User::getEmail, String.CASE_INSENSITIVE_ORDER);
            case 4 -> Comparator.comparing(User::getPassword);
            default -> null;
        };
    }
}
