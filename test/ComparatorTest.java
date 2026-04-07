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
                            .build(), -1),
                    Arguments.of(new User.BuilderUser(2)
                            .name("Никита")
                            .email("pochta@gmail.com")
                            .password("222222")
                            .build(), 0),
                    Arguments.of(new User.BuilderUser(3)
                            .name("Александр")
                            .email("Apochta@gmail.com")
                            .password("333333")
                            .build(), 1));
        }
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    void testGetComparator(User user2, int expectedValue) {
        var userComparator = comparator.getComparator(SortField.ID);

        User user1 = new User.BuilderUser(2)
                .name("Никита")
                .email("pochta@gmail.com")
                .password("222222")
                .build();

        Assertions.assertEquals(expectedValue, userComparator.compare(user2, user1));
    }
}
