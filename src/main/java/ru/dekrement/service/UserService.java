package ru.dekrement.service;

import ru.dekrement.model.User;

import java.util.List;

/**
 * Created by web on 06.03.2017.
 */
public interface UserService {
    void deleteUser(Integer id);
    void addUser(User user);
    void updateUser(User user);
    List<User> listUsers(String name, Integer offset, Integer maxResult);
    Long count(String name);
    User getUserById(int id);
}
