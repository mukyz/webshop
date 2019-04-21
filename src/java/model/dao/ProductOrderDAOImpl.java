/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.ProductOrder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dimitrije Muzur
 */
@Repository
public class ProductOrderDAOImpl implements ProductOrderDAO{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(ProductOrder order) {
        sessionFactory.getCurrentSession().saveOrUpdate(order);
    }

    @Override
    public List<ProductOrder> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from ProductOrder").list();
    }

    @Override
    public ProductOrder get(Integer id) {
        return (ProductOrder) sessionFactory.getCurrentSession().load(ProductOrder.class, id);
    }
    
}
