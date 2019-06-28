package inventory;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(length=25)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(length=25)
    private SubCategory subCategory;

    public Product(){ }

    Product(String name, int quantity, Category category, SubCategory subCategory){
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.subCategory = subCategory;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", category=" + category +
                ", subCategory=" + subCategory +
                '}';
    }
}
