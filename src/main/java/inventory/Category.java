package inventory;

import java.util.Arrays;
import java.util.List;

import static inventory.SubCategory.*;

public enum Category {

    CLOTHES(SHOES, SOCKS, JACKET),
    FOOD(FRUIT, CANDY, CAKE),
    HOUSEHOLD(SOFA, CHAIR);

    private SubCategory[] subCategories;


    Category(SubCategory ... subCategories){
        this.subCategories = subCategories;
    }

    public List<SubCategory> subCategories(){
        return Arrays.asList(this.subCategories);
    }


}
