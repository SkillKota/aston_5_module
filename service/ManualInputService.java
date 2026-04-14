package homework5.service;

import homework5.User;
import homework5.collection.UserList;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManualInputService implements InputService {
    @Override
    public UserList input(int size) {
        Scanner sc = new Scanner(System.in);
        UserList userList = new UserList();
        for (int i = 0; i < size; i++) {
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
                i--;
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат ID");
                sc.nextLine();
                i--;
            }
        }
        return userList;
    }

}
