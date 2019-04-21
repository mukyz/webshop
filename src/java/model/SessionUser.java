package model;

import java.util.HashMap;
import java.util.Map;
import model.service.ProductService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionUser implements DisposableBean{
    @Autowired
    ProductService productService;
    
    private String name;
    private int id;
    
    private Map<Product, Integer> cart;

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Product, Integer> cart) {
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SessionUser() {
        name = new String();
        cart = new HashMap<>(); 
   }   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
    
    public double getTotalCost(){
        double sum = 0;
        
        for(Map.Entry<Product, Integer> entry: cart.entrySet()){
            sum+= entry.getValue() * entry.getKey().getPrice();
        }
        
        return sum;
    }

    @Override
    public void destroy() throws Exception {
        if(!cart.isEmpty()){
            for(Map.Entry<Product, Integer> entry: cart.entrySet()){
                Product prod = productService.get(entry.getKey().getId());
                prod.setQuantity(prod.getQuantity() + entry.getValue());
                productService.add(prod);                
            }
        }
    }
}
