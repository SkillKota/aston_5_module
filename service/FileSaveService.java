package homework5.service;

import homework5.collection.UserList;

public interface FileSaveService {
    void append(UserList users, String filePath);
}
