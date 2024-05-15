package org.example.automation.validators;

import org.example.automation.pages.CartPage;
import org.example.models.Product;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CartPageValidator {
    private final CartPage cartPage;

    public CartPageValidator(CartPage page){
        this.cartPage = page;
    }

    public void validateCartProducts(List<Product> cartProducts, List<Product> addedProducts) {
        Assert.assertEquals(cartProducts.size(), addedProducts.size());
        SoftAssert sa = new SoftAssert();

        for (Product addedProduct : addedProducts) {
            boolean productFound = false;
            for (Product cartProduct : cartProducts) {
                if(!cartProduct.name().contentEquals(addedProduct.name()))
                    continue;
                productFound = true;
                Assert.assertEquals(addedProduct.name(), cartProduct.name());
                Assert.assertEquals(addedProduct.description(), cartProduct.description());
                Assert.assertEquals(addedProduct.price(), cartProduct.price());
            }
            sa.assertTrue(productFound);
        }

        sa.assertAll();
    }
}
