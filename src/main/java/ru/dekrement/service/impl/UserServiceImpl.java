package ru.dekrement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dekrement.dao.UserDAO;
import ru.dekrement.model.User;
import ru.dekrement.service.UserService;

import java.util.List;

/**
 * Created by web on 06.03.2017.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void deleteUser(Integer id) {
        userDAO.deleteUser(id);
    }


    @Transactional
    public void addUser(User user) {
        this.userDAO.addUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public List<User> listUsers(String name, Integer offset, Integer maxResult) {
        return userDAO.listUsers(name, offset, maxResult);
    }

    public Long count(String name) {
        return userDAO.count(name);
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

}
