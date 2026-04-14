package homework5.test;

import homework5.collection.UserList;
import homework5.service.ManualInputService;

public class ManualInputTest {

    static void main(String[] args) {
        UserList userList = new ManualInputService().input(5);
        userList.toList().forEach(System.out::println);
    }

}
