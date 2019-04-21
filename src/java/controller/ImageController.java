/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Image;
import model.Product;
import model.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Dimitrije Muzur
 */

@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;
    
    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public String getImage(@PathVariable String id,ModelMap model){
        Image img = imageService==null? null: imageService.getImage(Integer.parseInt(id));
        model.addAttribute("image", img);
        return "partial/image";
    }
    
    @RequestMapping(value = "/image/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteImage(@PathVariable String id){
        Image image = imageService.getImage(Integer.valueOf(id));
        imageService.removeImage(image);
        return image.getFilename();
    }
}
