package homework5.service;

import homework5.User;
import homework5.util.SortField;

import java.util.Comparator;

public interface UserComparatorProvider {
    Comparator<User> getComparator(SortField sortField);
}
