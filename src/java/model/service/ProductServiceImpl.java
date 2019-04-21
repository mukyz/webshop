/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import model.service.ProductService;
import java.util.List;
import model.Product;
import model.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dimitrije Muzur
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductDAO productDAO;

    @Override
    @Transactional
    public void add(Product product) {
        productDAO.addProduct(product);
    }

    @Override
    @Transactional
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    @Transactional
    public Product get(int id) {
        return productDAO.get(id);
    }

    @Override
    @Transactional
    public void remove(Product product) {
        productDAO.remove(product);
    }  
}
