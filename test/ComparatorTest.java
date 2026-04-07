package homework5.test;

import homework5.User;
import homework5.service.UserComparator;
import homework5.util.SortField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

public class ComparatorTest {
    private final UserComparator comparator = new UserComparator();

    static class UserArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new User.BuilderUser(1)
                            .name("Ярослав")
                            .email("Zpochta@gmail.com")
                            .password("111111")
                            .build()),
                    Arguments.of(new User.BuilderUser(2)
                            .name("Никита")
                            .email("pochta@gmail.com")
                            .password("222222")
                            .build()),
                    Arguments.of(new User.BuilderUser(3)
                            .name("Александр")
                            .email("Apochta@gmail.com")
                            .password("333333")
                            .build())
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    void testGetComparator(User user2) {
        User user1 = new User.BuilderUser(2)
                .name("Никита")
                .email("pochta@gmail.com")
                .password("222222")
                .build();

        int expectedById = Integer.compare(user2.getId(), user1.getId());
        int expectedByName = user2.getName().compareToIgnoreCase(user1.getName());
        int expectedByEmail = user2.getEmail().compareToIgnoreCase(user1.getEmail());
        int expectedByPassword = user2.getPassword().compareTo(user1.getPassword());

        var idComparator = comparator.getComparator(SortField.ID);
        var nameComparator = comparator.getComparator(SortField.NAME);
        var emailComparator = comparator.getComparator(SortField.EMAIL);
        var passwordComparator = comparator.getComparator(SortField.PASSWORD);

        Assertions.assertEquals(expectedById, idComparator.compare(user2, user1));
        Assertions.assertEquals(expectedByName, nameComparator.compare(user2, user1));
        Assertions.assertEquals(expectedByEmail, emailComparator.compare(user2, user1));
        Assertions.assertEquals(expectedByPassword, passwordComparator.compare(user2, user1));
    }
}
