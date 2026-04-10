package homework5.test;

import homework5.collection.UserList;
import homework5.service.FileSaveUserService;
import homework5.service.RandomUserGenerator;

public class FileSaveServiceTest {

    static void main() {
        RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
        UserList userList = randomUserGenerator.input(3);
        FileSaveUserService fileSaveUserService = new FileSaveUserService();
        fileSaveUserService.append(userList, "users.txt");
    }

}
