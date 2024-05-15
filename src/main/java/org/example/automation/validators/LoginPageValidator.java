package org.example.automation.validators;

import org.example.automation.pages.LoginPage;
import org.example.automation.pages.ProductPage;
import org.testng.Assert;

public class LoginPageValidator {
    private final LoginPage loginPage;

    public LoginPageValidator(LoginPage page){
        this.loginPage = page;
    }

    public void redirectedToProductPageAfterLogin(){
        String url = loginPage.getCurrentUrl();
        Assert.assertEquals(url, ProductPage.URL);
    }
}
