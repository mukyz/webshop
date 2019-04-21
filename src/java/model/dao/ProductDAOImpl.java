/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.dao.ProductDAO;
import java.util.List;
import model.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dimitrije Muzur
 */
@Repository
public class ProductDAOImpl implements ProductDAO{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addProduct(Product product) {
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

    @Override
    public List<Product> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Product").list();
    }

    @Override
    public Product get(int id) {
        return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public void remove(Product product) {
        sessionFactory.getCurrentSession().delete(product);
    }    
}
