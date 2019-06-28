package inventory;

import org.springframework.stereotype.Component;

@Component
class ProductRules {


    public boolean validateCategory(Product p){
        return (p.getCategory().subCategories().contains(p.getSubCategory()) );
    }

    public boolean validateQuantity(Product p){
        return p.getQuantity() > 0;
    }

}
