package inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator {

    @Autowired
    private ProductRules rules;

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Product product = (Product) o;

        if (!rules.validateQuantity(product)) {

            errors.rejectValue("quantity", "Product.quantity", "Quantity is negative");

        } else if (!rules.validateCategory(product)) {

            errors.rejectValue("subCategory", "Product.subCategory", "SubCategory mismatch");
        }

    }

}