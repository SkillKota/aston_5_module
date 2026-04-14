package homework5.service;

import homework5.collection.UserList;

import java.io.FileWriter;
import java.io.IOException;

public class FileSaveUserService implements FileSaveService {
    @Override
    public void append(UserList users, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            users.toList().forEach(user -> {
                try {
                    fileWriter.write(user.getId() + "," + user.getName() + "," + user.getEmail() + "," + user.getPassword());
                    fileWriter.append("\n");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
