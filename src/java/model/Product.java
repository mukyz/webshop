package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "product")
public class Product implements Serializable {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @NotEmpty
    @Column(name = "name")
    private String name;
    
    @DecimalMin(value = "0")
    @Column(name = "price")
    private double price;
    
    @NotEmpty
    @Column(name = "description")
    @Lob
    private String description;
    
    @Min(value = 0)
    @Column(name = "quantity")
    private int quantity;
    
    @OneToMany(orphanRemoval = true, mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Image> images;
    
    @ManyToOne
    private ProductOrder productOrder;

    public Set<Image> getImages() {
        return images;
    }

    public Product() {
        images = new HashSet<>();
    }   

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        return id != null && id.equals(other.getId());
    }
    
    @Override
    public int hashCode() {
        return 49;
    }  
}
