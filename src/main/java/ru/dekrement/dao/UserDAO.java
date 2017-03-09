package ru.dekrement.dao;

import ru.dekrement.model.User;

import java.util.List;

/**
 * Created by web on 06.03.2017.
 */
public interface UserDAO {
    void deleteUser(Integer id);
    void addUser(User user);
    List<User> listUsers(String name, Integer offset, Integer maxResult);
    Long count(String name);
    User getUserById(Integer id);
    void removeUser(int id);

    void updateUser(User user);
}
