package org.example.automation.validators;

import org.example.automation.pages.CheckoutOnePage;
import org.testng.Assert;

public class CheckoutOnePageValidator {
    private static final String FIRST_NAME_MISSING_ERROR = "Error: First Name is required";
    private static final String LAST_NAME_MISSING_ERROR = "Error: Last Name is required";
    private static final String POSTAL_CODE_MISSING_ERROR = "Error: Postal Code is required";
    private final CheckoutOnePage checkoutOnePage;

    public CheckoutOnePageValidator(CheckoutOnePage page){
        this.checkoutOnePage = page;
    }

    public void validateContinueButton(){ Assert.assertNotNull(checkoutOnePage.getContinueButton()); }

    public void validateCancelButton(){ Assert.assertNotNull(checkoutOnePage.getCancelButton()); }

    public void validateEmptyForm(){
        checkoutOnePage.clearForm();
        checkoutOnePage.submitForm();
        validateErrorMessage(FIRST_NAME_MISSING_ERROR);
    }

    public void validateLastNameErrorMessage(){
        checkoutOnePage.clearForm();
        checkoutOnePage.inputFirstName("Sauce");
        checkoutOnePage.submitForm();
        validateErrorMessage(LAST_NAME_MISSING_ERROR);
    }

    public void validatePostalCodeErrorMessage(){
        checkoutOnePage.clearForm();
        checkoutOnePage.inputFirstName("Sauce");
        checkoutOnePage.inputLastName("Demo");
        checkoutOnePage.submitForm();
        validateErrorMessage(POSTAL_CODE_MISSING_ERROR);
    }

    private void validateErrorMessage(String expectedMessage) {
        String errorMessage = checkoutOnePage.readErrorMessage();
        Assert.assertEquals(errorMessage, expectedMessage, "Error message does not match");
    }
}
