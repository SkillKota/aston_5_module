package homework5.test;

import homework5.User;
import homework5.collection.UserList;
import homework5.service.MultiThreadCounter;

public class MultiThreadCounterTest {

    static void main() {
        UserList userList = new UserList();
        userList.add(new User.BuilderUser(1).name("dsadas").email("dsakdksa@jdas.js").password("12312j3i12j3").build());
        userList.add(new User.BuilderUser(1).name("dsadas").email("dsakdksa@jdas.js").password("12312j3i12j3").build());
        userList.add(new User.BuilderUser(1).name("dsadas").email("dsakdksa@jdas.js").password("12312j3i12j3").build());
        userList.add(new User.BuilderUser(1).name("dsadas").email("dsakdksa@jdas.js").password("12312j3i12j3").build());
        userList.add(new User.BuilderUser(1).name("dsadas").email("dsakdksa@jdas.js").password("12312j3i12j3").build());
        userList.add(new User.BuilderUser(1).name("dsadas").email("dsakdksa@jdas.js").password("12312j3i12j3").build());
        userList.add(new User.BuilderUser(1).name("dsadas").email("dsakdksa@jdas.js").password("12312j3i12j3").build());

        MultiThreadCounter multiThreadCounter = new MultiThreadCounter();
        int i = multiThreadCounter.countById(userList, 1);
        System.out.println(i);
    }

}
