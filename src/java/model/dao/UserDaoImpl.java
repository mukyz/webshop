/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dimitrije Muzur
 */

@Repository
public class UserDaoImpl implements UserDAO{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public List<User> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public User get(int id) {
        return (User) sessionFactory.getCurrentSession().load(User.class, id);
    }

    @Override
    public void remove(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public User checkCredentials(User user) {
        List<User> users = sessionFactory.getCurrentSession().createQuery("from User as user where user.username='"+user.getUsername()+"' and user.password= '"+user.getPassword()+"'").list();
        if(users.isEmpty()) return null;
        else return users.get(0);
    }
}
