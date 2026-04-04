package homework5.service;

import homework5.User;
import homework5.util.SortField;

import java.util.Comparator;

public class UserComparator implements UserComparatorProvider {
    @Override
    public Comparator<User> getComparator(SortField sortField) {
        return switch (sortField.getMenuNumber()) {
            case 1 -> (o1, o2) -> o1.getId() - o2.getId();
            case 2 -> (o1, o2) -> o1.getName().compareTo(o2.getName());
            case 3 -> (o1, o2) -> o1.getEmail().compareTo(o2.getEmail());
            case 4 -> (o1, o2) -> o1.getPassword().compareTo(o2.getPassword());
            default -> null;
        };
    }
}
