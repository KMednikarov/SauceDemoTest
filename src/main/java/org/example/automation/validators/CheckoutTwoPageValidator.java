package org.example.automation.validators;

import org.example.automation.pages.CheckoutTwoPage;
import org.example.models.Product;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CheckoutTwoPageValidator {
    private final CheckoutTwoPage checkoutTwoPage;

    public CheckoutTwoPageValidator(CheckoutTwoPage page){
        this.checkoutTwoPage = page;
    }

    public void validateProducts(List<Product> expectedProducts){
        List<Product> checkoutProducts =  checkoutTwoPage.getCheckoutProducts();

        Assert.assertEquals(checkoutProducts.size(), expectedProducts.size(), "Products count does not match");
        SoftAssert sa = new SoftAssert();

        for (Product expectedProduct : expectedProducts) {
            boolean productFound = false;
            for (Product checkoutProduct : checkoutProducts) {
                if(!expectedProduct.name().contentEquals(checkoutProduct.name()))
                    continue;
                productFound = true;
                Assert.assertEquals(checkoutProduct.name(), expectedProduct.name(), "Product name does not match");
                Assert.assertEquals(checkoutProduct.description(), expectedProduct.description(), "Product description does not match");
                Assert.assertEquals(checkoutProduct.price(), expectedProduct.price(), "Product price does not match");
            }
            sa.assertTrue(productFound, "Product not found in checkout");
        }

        sa.assertAll();
    }

    public void validateSubTotalPrice(){
        double actualTotal = checkoutTwoPage.getSubTotalPrice();
        double expectedTotal = checkoutTwoPage.getTestContext().getCart().getSubTotalPrice();
        Assert.assertEquals(actualTotal, expectedTotal);
    }

    public void validateTaxPrice(){
        double actualTotal = checkoutTwoPage.getTaxPrice();
        double expectedTotal = checkoutTwoPage.getTestContext().getCart().getTaxPrice();
        Assert.assertEquals(actualTotal, expectedTotal);
    }

    public void validateTotalPrice(){
        double actualTotal = checkoutTwoPage.getTotalPrice();
        double expectedTotal = checkoutTwoPage.getTestContext().getCart().getTotalPrice();
        Assert.assertEquals(actualTotal, expectedTotal);
    }
}
