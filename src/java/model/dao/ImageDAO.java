/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.Image;

/**
 *
 * @author Dimitrije Muzur
 */
public interface ImageDAO {
    public void addImage(Image image);
    public List<Image> listImages();
    public Image getImage(int id);
    public void removeImage(Image image);
}
