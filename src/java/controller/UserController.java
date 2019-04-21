package controller;

import javax.validation.Valid;
import model.SessionUser;
import model.User;
import model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Dimitrije Muzur
 */

@Controller
public class UserController {
    @Autowired
    UserService userService;
    
    @Autowired
    SessionUser sessionUser;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(ModelMap model, RedirectAttributes ra){
        if(sessionUser.getName().isEmpty()){
            model.addAttribute("user", new User());
            model.addAttribute("title", "Prijava");
            model.addAttribute("suser", sessionUser);
            return "login";     
        } else {
            ra.addFlashAttribute("message", "Vec ste prijavljeni!");
            return "redirect:/";
        }
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(@ModelAttribute User user, ModelMap model, RedirectAttributes ra){
        User check = userService.checkCredentials(user);
        
        if(check == null){
            model.addAttribute("error", "Korisnik sa datim parametrima ne postoji.");
            return "login";
        }
        
        sessionUser.setName(check.getName());
        sessionUser.setId(check.getId());
        
        ra.addFlashAttribute("message", "Uspesno ste se ulogovali kao "+sessionUser.getName());
        return "redirect:/";       
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(RedirectAttributes ra){
        if(!sessionUser.getName().isEmpty()){
            sessionUser.setName(""); 
            sessionUser.setId(0);
            ra.addFlashAttribute("message", "Uspesno ste se odjavili");
        } else {
            ra.addFlashAttribute("error", "Niste prijavljeni!");
        }
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(ModelMap model, RedirectAttributes ra){
        if(sessionUser.getName().isEmpty()){
            model.addAttribute("suser", sessionUser);
            model.addAttribute("user", new User());
            model.addAttribute("actionUrl", "registration");
            model.addAttribute("title", "Registracija");
            return "registration";
        } else {
            ra.addFlashAttribute("message", "Vec ste ulogovani. Izlogujte se da napravite novi nalog");
            return "redirect:/"; 
        }
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute @Valid User user, BindingResult result, RedirectAttributes ra){
        if(result.hasErrors()){
            return "registration";
        }
        
        userService.addUser(user);
        
        ra.addFlashAttribute("message", "Uspesno ste kreirali novi nalog.");
        return "redirect:/";       
    }    
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(ModelMap model){
        model.addAttribute("suser", sessionUser);
        model.addAttribute("users", userService.getAll());
        model.addAttribute("title", "Korisnici");
        return "users";        
    }
    
    @RequestMapping (value = "/user/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable String id, ModelMap model){
        User user = userService.get(Integer.parseInt(id));
        model.addAttribute("title", user.getName());
        model.addAttribute("user", user);
        model.addAttribute("suser", sessionUser);
        model.addAttribute("actionUrl", "/user/edit/"+id);
        model.addAttribute("title", "Izmeni korisnika sa id: "+id);
        return "registration";
    } 
    
    @RequestMapping (value = "/user/edit/{id}", method = RequestMethod.POST)
    public String editUserPost(@ModelAttribute @Valid User user, BindingResult result, RedirectAttributes ra){
        if(result.hasErrors()){
            
            return "registration";
        }
        if(user.getId()==sessionUser.getId()){
            sessionUser.setName(user.getName());
        }
        userService.addUser(user);
        ra.addFlashAttribute("message", "Korisnik je uspesno izmenjen");
        return "redirect:/users";
    }
    
    @RequestMapping (value = "/user/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id, RedirectAttributes ra){
        User user = userService.get(Integer.parseInt(id));
        if(sessionUser.getId() == user.getId()){
            sessionUser.setId(0);
            sessionUser.setName("");
        }
        userService.remove(user);
        
        ra.addFlashAttribute("message", "Korisnik je obrisan");
        return "redirect:/users";
    }
}
