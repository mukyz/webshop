/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.User;

/**
 *
 * @author Dimitrije Muzur
 */
public interface UserDAO {
    public void addUser(User user);
    public List<User> getAll();
    public User get(int id);
    public void remove(User user);    
    public User checkCredentials(User user);
}
