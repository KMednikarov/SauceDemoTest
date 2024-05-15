package org.example.automation.validators;

import org.example.automation.pages.ProductPage;
import org.testng.Assert;

public class ProductPageValidator {
    private final ProductPage productPage;

    public ProductPageValidator(ProductPage page){
        this.productPage = page;
    }

    public void validateProductsAddedToCart(){
        int badgeCount = productPage.getCartBadgeCount();
        int addedProductsCount = productPage.getAddedProducts().size();
        Assert.assertEquals(badgeCount, 2);
        Assert.assertEquals(addedProductsCount, badgeCount);
    }

    public void validateProductsRemovedFromCart(){
        int badgeCount = productPage.getCartBadgeCount();
        boolean addedProductsNotFound = productPage.getAddedProducts().isEmpty();
        Assert.assertEquals(badgeCount, 0);
        Assert.assertTrue(addedProductsNotFound);
    }
}
