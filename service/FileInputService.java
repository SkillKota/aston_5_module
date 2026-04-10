package homework5.service;

import homework5.User;
import homework5.collection.UserList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileInputService implements InputService {
    @Override
    public UserList input(int size) {
        UserList userList = new UserList();
        FileReader fileReader;
        try {
            try {
                fileReader = new FileReader("users.txt");
            } catch (FileNotFoundException e) {
                new File("users.txt").createNewFile();
                fileReader = new FileReader("users.txt");
            }
            StringBuilder stringBuilder = new StringBuilder();
            int c;
            while ((c = fileReader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            fileReader.close();
            if (stringBuilder.toString().equals("")) {
                System.out.println("Файл с пользователями пуст");
                return new UserList();
            }
            for (String str : stringBuilder.toString().split("\n")) {
                String[] split = str.split(",");
                userList.add(new User.BuilderUser(Integer.parseInt(split[0]))
                        .name(split[1])
                        .email(split[2])
                        .password(split[3])
                        .build());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

}
