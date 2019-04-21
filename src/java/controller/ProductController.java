package controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import model.Image;
import model.service.ImageService;
import model.Product;
import model.SessionUser;
import model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ImageService imageService;
    
    @Autowired
    private SessionUser sessionUser;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String showProducts(ModelMap model){
        model.addAttribute("title", "Proizvodi");
        model.addAttribute("products", productService.getAll());
        model.addAttribute("suser", sessionUser);
        
        return "products";
    }
    
    @RequestMapping(value="/add_product", method = RequestMethod.GET)
    public String addProductForm(ModelMap model, RedirectAttributes ra){
        if(sessionUser.getName().isEmpty()){
            ra.addFlashAttribute("error", "Morate biti prijavljeni da biste dodali proizovd");            
            return "redirect:/";
        }
        model.addAttribute("actionUrl", "/add_product");
        model.addAttribute("title", "Novi Proizovd");
        model.addAttribute("product", new Product());
        model.addAttribute("suser", sessionUser);
        return "addProduct";
    }
    
    @RequestMapping(value={"/add_product", "/product/edit/{id}"}, method = RequestMethod.POST)
    public String addProduct(
            @RequestParam("upFiles") MultipartFile[] uploadingFiles, 
            @ModelAttribute("product")@Valid Product product, 
            BindingResult result, 
            ModelMap model, 
            RedirectAttributes ra){
        
        if(result.hasErrors()){
            return "addProduct";
        }
        
        productService.add(product);
        
        for(MultipartFile upFile: uploadingFiles){
            if(upFile.getSize() != 0){
                try {                    
                    Image img = new Image();
                    img.setFilename(upFile.getOriginalFilename());
                    img.setContent(upFile.getBytes());
                    img.setProduct(product);                    
                    imageService.addImage(img);
                } catch (IOException ex) {
                }
            } 
        }       
        
        ra.addFlashAttribute("message", "Proizovd " + product.getName() + " je uspesno sacuvan.");
        return "redirect:/";
    } 
   
    @RequestMapping (value = "/product/{id}", method = RequestMethod.GET)
    public String singleProduct(@PathVariable String id, ModelMap model){
        Product product = productService.get(Integer.parseInt(id));
        model.addAttribute("title", product.getName());
        model.addAttribute("product", product);
        model.addAttribute("suser", sessionUser);
        return "product";
    } 
    
    @RequestMapping (value = "/product/edit/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable String id, ModelMap model, RedirectAttributes ra){
        Product product = productService.get(Integer.parseInt(id));
        if(product!=null){
            model.addAttribute("actionUrl", "/product/edit/"+id);
            model.addAttribute("title", product.getName());
            model.addAttribute("product", product);
            model.addAttribute("suser", sessionUser);
            return "/addProduct";
        } else {
            ra.addFlashAttribute("error", "Proizvod ne postoji!");
            return "redirect:/";
            
        }
    } 
    
    @RequestMapping (value = "/product/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable String id, ModelMap model, RedirectAttributes ra){        
        if(!sessionUser.getName().isEmpty()){
            Product product = productService.get(Integer.parseInt(id));
            productService.remove(product);
            
            sessionUser.getCart().remove(product);
            
            ra.addFlashAttribute("message", "Proizvod je obrisan!");
            return "redirect:/";
        } else {
            ra.addFlashAttribute("error", "Niste prijavljeni!");
            return "redirect:/";            
        }
    } 
    
    @RequestMapping (value = "/search", method = RequestMethod.POST)
    public String searchProduct(@RequestParam("search") String searchString, ModelMap model){
        List<Product> allProds = productService.getAll();
        List<Product> resultList = new ArrayList<>();
        
        for(Product prod: allProds){
            if(prod.getName().contains(searchString) || prod.getDescription().contains(searchString)){
                resultList.add(prod);
            }
        }

        model.addAttribute("title", "Pretraga");
        
        if(resultList.isEmpty()){
            model.addAttribute("error", "Za zadati kriterijum nema proizvoda");
        } else {
            model.addAttribute("products", resultList);
        }
        
        model.addAttribute("suser", sessionUser);
        return "products";
    } 
    
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(ModelMap model){
        model.addAttribute("title", "O meni");
        model.addAttribute("suser", sessionUser);
        return "about";
    }
}
