/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.dao.ImageDAO;
import java.util.List;
import model.Image;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dimitrije Muzur
 */

@Repository
public class ImageDAOImpl implements ImageDAO{
    @Autowired
    private SessionFactory sessionFactory;
    
    public void addImage(Image image) {
        sessionFactory.getCurrentSession().saveOrUpdate(image);
    }
    public List<Image> listImages() {
        return sessionFactory.getCurrentSession().createQuery("from Image").list();
    }
    public Image getImage(int id) {
        return (Image) sessionFactory.getCurrentSession().load(Image.class, id);
    }
    public void removeImage(Image image) {        
        if(image!=null) {
            sessionFactory.getCurrentSession().delete(image);
        }
    }    
}
