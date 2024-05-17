package org.example.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.context.TestContext;
import org.example.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage{
    public static final String URL = Constants.BASE_URL + "/checkout-complete.html";
    private final By CheckoutCompleteMessage = By.xpath("//div[@data-test='complete-text']");
    private final By BackHome = By.xpath("//button[@data-test='back-to-products']");

    public CheckoutCompletePage(WebDriver driver, TestContext testContext) {
        super(driver, testContext, LogManager.getLogger(CheckoutCompletePage.class));
    }

    public String getCheckoutMessage(){
        return getDriver().findElement(CheckoutCompleteMessage).getText();
    }

    public void goBackToHome() {
        getDriver().findElement(BackHome).click();
    }
}
