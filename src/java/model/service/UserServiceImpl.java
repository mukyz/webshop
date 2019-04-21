/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.util.List;
import model.User;
import model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dimitrije Muzur
 */

@Repository
public class UserServiceImpl  implements UserService{
    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    @Transactional
    public User get(int id) {
        return userDAO.get(id);
    }

    @Override
    @Transactional
    public void remove(User user) {
        userDAO.remove(user);
    } 

    @Override
    public User checkCredentials(User user) {
        return userDAO.checkCredentials(user);
    }
}
