package homework5.service;

import homework5.User;
import homework5.collection.UserList;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ManualInputService implements InputService {
    @Override
    public UserList input(int size) {
        return null;
    }

    public UserList getUserList() {
        Scanner sc = new Scanner(System.in);
        boolean next = true;
        UserList userList = new UserList();

        while (next) {
            try {
                System.out.print("Введите ID: ");
                int id = sc.nextInt();
                System.out.print("Введите имя: ");
                String name = sc.next();
                System.out.print("Введите email: ");
                String email = sc.next();
                System.out.print("Введите пароль: ");
                String password = sc.next();
                userList.add(new User.BuilderUser(id).name(name).email(email).password(password).build());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (InputMismatchException e) {
                System.out.println("Не верный формат ID");
                continue;
            }
            System.out.print("Добавить еще пользователя?\n1 - добавить\n2 - завершить добавление");
            try {
                int i = sc.nextInt();
                switch (i) {
                    case 1:
                        System.out.println("Добавляем еще одного пользователя");
                        next = true;
                        break;
                    case 2:
                        System.out.println("Завершение добавления пользователей");
                        next = false;
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.print("Добавить еще пользователя?\n1 - добавить\n2 - завершить добавление");
            }
        }
        return userList;
    }

}
