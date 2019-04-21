/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import model.Product;
import model.ProductOrder;
import model.ProductOrderItems;
import model.SessionUser;
import model.service.ProductOrderItemsService;
import model.service.ProductOrderService;
import model.service.ProductService;
import model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Dimitrije Muzur
 */

@Controller
public class OrderController {
    @Autowired
    SessionUser sessionUser;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    ProductOrderService productOrderService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    ProductOrderItemsService productOrderItemService;
    
    @RequestMapping(value = "/add_to_cart")
    @ResponseBody
    public String addToCart(@RequestParam(value = "prodId") Integer id,
            @RequestParam(value = "quantity") Integer quantity) throws InterruptedException{
        Product prod = productService.get(id);
        if(prod.getQuantity() >= quantity){
            
            if(sessionUser.getCart().containsKey(prod)){
                sessionUser.getCart().put(prod, sessionUser.getCart().get(prod) + quantity);
            } else {
                sessionUser.getCart().put(productService.get(id), quantity);
            }
            
            prod.setQuantity(prod.getQuantity()-quantity);
            productService.add(prod);
        }     
        
        String ret = new String();
        ret+=String.valueOf(prod.getQuantity()) + "#" 
                +String.valueOf(sessionUser.getCart().size())+"#"
                +String.valueOf(sessionUser.getTotalCost());
        
        
        //Thread.sleep(2000);
        
        return ret;
    }
    
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String getCart(ModelMap model) {
        model.addAttribute("title", "Korpa");
        model.addAttribute("suser", sessionUser);
        return "cart";
    }
    
    @RequestMapping (value = "/cart/remove/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable Integer id, ModelMap model, RedirectAttributes ra){        
        Product prod = productService.get(id);        
        Integer res = sessionUser.getCart().remove(prod);        
        if(res == null){
            ra.addFlashAttribute("error", "U korpi ne postoji izabrani proizvod");
        } else {
            prod.setQuantity(prod.getQuantity()+res);
            productService.add(prod);
            ra.addFlashAttribute("message", "Stavka uklonjena");
        }
        return "redirect:/cart";
    } 
    
    @RequestMapping(value = "/cart/order", method = RequestMethod.GET)
    public String placeOrder(ModelMap model, RedirectAttributes ra) {
        
        ProductOrder order = new ProductOrder();
        order.setUser(userService.get(sessionUser.getId()));
        productOrderService.save(order);
        
        for(Map.Entry<Product, Integer> entry: sessionUser.getCart().entrySet()){
            ProductOrderItems item = new ProductOrderItems();
            item.setProductItem(productService.get(entry.getKey().getId()));
            item.setQuantity(entry.getValue());
            item.setOrder(order);
            
            productOrderItemService.save(item);            
        }
        
        sessionUser.setCart(new HashMap<>());
        ra.addFlashAttribute("message", "Vasa narudzbina je zaprimljena");
        return "redirect:/";
        
    }  
    
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getOrders(ModelMap model){
        model.addAttribute("title", "Narudzbine");
        model.addAttribute("suser", sessionUser);
        model.addAttribute("orders", productOrderService.getAll());       
        
        return "orders";
    }
    
    @RequestMapping (value = "/order/{id}", method = RequestMethod.GET)
    public String singleOrder(@PathVariable String id, ModelMap model){
        ProductOrder order = productOrderService.get(Integer.parseInt(id));
        model.addAttribute("title", "Narudzbina br:"+order.getId());
        model.addAttribute("order", order);
        model.addAttribute("suser", sessionUser);
        return "order";
    } 
}
