/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.ProductOrderItems;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dimitrije Muzur
 */
@Repository
public class ProductOrderItemsDAOImpl implements ProductOrderItemsDAO{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(ProductOrderItems item) {
        sessionFactory.getCurrentSession().saveOrUpdate(item);
    }
    
}
