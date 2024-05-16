package org.example.automation.validators;

import org.example.automation.pages.CheckoutCompletePage;
import org.testng.Assert;

public class CheckoutCompletePageValidator {
    private final CheckoutCompletePage checkoutCompletePage;

    public CheckoutCompletePageValidator(CheckoutCompletePage page){
        this.checkoutCompletePage = page;
    }

    public void validateCheckoutSuccessMessage(){
        String actualMessage = checkoutCompletePage.getCheckoutMessage();
        String expectedMessage = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

        Assert.assertEquals(actualMessage, expectedMessage, "Checkout success message does not match");
    }
}
