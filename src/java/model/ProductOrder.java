/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Dimitrije Muzur
 */
@Entity
@Table(name = "product_order")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    private User user;
    
    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL)
    private Set<ProductOrderItems> orderItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ProductOrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<ProductOrderItems> orderItems) {
        this.orderItems = orderItems;
    }
    
    public double getTotalPrice(){
        double sum = 0;
        
        for(ProductOrderItems item : orderItems){
            sum += item.getQuantity() * item.getProductItem().getPrice();
        }
        
        return sum;
    }
}
