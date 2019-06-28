package inventory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRulesTest {

    @Autowired
    private ProductRules rules;
    
    @Test
    public void shouldMatchSubCategory(){
        Product product = new Product("Nike11", 100, Category.CLOTHES, SubCategory.SHOES);

        Assert.assertTrue(rules.validateCategory(product));

    }

    @Test
    public void shouldMismatchClothes(){
        Product product = new Product("Nike11", 100, Category.CLOTHES, SubCategory.CAKE);

        Assert.assertFalse(rules.validateCategory(product));

    }


    @Test
    public void shouldMismatchFood(){
        Product product = new Product("Nike11", 100, Category.FOOD, SubCategory.SHOES);
        Assert.assertFalse(rules.validateCategory(product));

    }

    @Test
    public void shouldMismatchHousehold(){
        Product product = new Product("Nike11", 100, Category.HOUSEHOLD, SubCategory.SHOES);

        Assert.assertFalse(rules.validateCategory(product));

    }

}
