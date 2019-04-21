/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import model.service.ImageService;
import java.util.List;
import javax.transaction.Transactional;
import model.Image;
import model.dao.ImageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dimitrije Muzur
 */

@Service
public class ImageServiceImpl implements ImageService{
    
    @Autowired
    private ImageDAO imageDAO;
    
    @Transactional
    public void addImage(Image image) {
        imageDAO.addImage(image);
    }

    @Transactional
    public List<Image> listImages() {
        return imageDAO.listImages();
    }

    @Transactional
    public Image getImage(int id) {
        return imageDAO.getImage(id);
    }

    @Transactional
    public void removeImage(Image image) {
        imageDAO.removeImage(image);
    }    
}
