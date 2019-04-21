/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.util.List;
import model.Product;

/**
 *
 * @author Dimitrije Muzur
 */
public interface ProductService {
    public void add(Product product);
    public List<Product> getAll();
    public Product get(int id);
    public void remove(Product product);
}
