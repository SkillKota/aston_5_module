package homework5.test;

import homework5.collection.UserList;
import homework5.service.ManualInputService;

public class ManualInputTest {

    static void main() {
        UserList userList = new ManualInputService().getUserList();
    }

}
