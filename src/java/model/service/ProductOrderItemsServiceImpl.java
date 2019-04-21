/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import model.ProductOrderItems;
import model.dao.ProductOrderItemsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dimitrije Muzur
 */
@Service
public class ProductOrderItemsServiceImpl implements ProductOrderItemsService{
    
    @Autowired
    ProductOrderItemsDAO productOrderItemsDAO;

    @Override
    @Transactional
    public void save(ProductOrderItems item) {
        productOrderItemsDAO.save(item);
    }  
}
