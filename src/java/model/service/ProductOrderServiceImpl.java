/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.util.List;
import model.ProductOrder;
import model.dao.ProductOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dimitrije Muzur
 */
@Service
public class ProductOrderServiceImpl implements ProductOrderService{
    @Autowired
    ProductOrderDAO productOrderDAO;

    @Override
    @Transactional
    public void save(ProductOrder order) {
        productOrderDAO.save(order);
    }

    @Override
    @Transactional
    public List<ProductOrder> getAll() {
        return productOrderDAO.getAll();
    }

    @Override
    @Transactional
    public ProductOrder get(Integer id) {
        return productOrderDAO.get(id);
    }
}
