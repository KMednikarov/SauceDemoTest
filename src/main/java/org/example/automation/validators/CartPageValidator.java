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
                Assert.assertEquals(addedProduct.name(), cartProduct.name(), "Product name does not match");
                Assert.assertEquals(addedProduct.description(), cartProduct.description(), "Product description does not match");
                Assert.assertEquals(addedProduct.price(), cartProduct.price(), "Product price does not match");
            }
            sa.assertTrue(productFound, "Product not found in cart");
        }

        sa.assertAll();
    }
}
