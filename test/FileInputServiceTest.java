package homework5.test;

import homework5.collection.UserList;
import homework5.service.FileInputService;

public class FileInputServiceTest {

    static void main() {

        FileInputService fileInputService = new FileInputService();
        UserList userList= fileInputService.input(5);
        userList.toList().forEach(System.out::println);

    }

}
