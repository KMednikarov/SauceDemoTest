package org.example.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.example.context.TestContext;
import org.example.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutOnePage extends BasePage{
    public static final String URL = Constants.BASE_URL + "/checkout-step-one.html";
    private By FirstName = By.xpath("//*[@id='first-name']");
    private By LastName = By.xpath("//*[@id='last-name']");
    private By PostalCode = By.xpath("//*[@id='postal-code']");
    private By CancelButton = By.xpath("//*[@id='cancel']");
    private By ContinueButton = By.xpath("//*[@id='continue']");
    private By ErrorMessage = By.xpath("//*[@id='checkout_info_container']/div/form/div[1]/div[4]/h3");
    private By ErrorButton = By.xpath("//button[@data-test = 'error-button']");
    private boolean hasErrors = false;

    public CheckoutOnePage(WebDriver driver, TestContext testContext) {
        super(driver, testContext, LogManager.getLogger(CheckoutOnePage.class));
    }
    public WebElement getContinueButton(){
        List<WebElement> continueButton = getDriver().findElements(ContinueButton);
        if(continueButton.size() == 0){
            return null;
        }
        return continueButton.get(0);
    }
    public WebElement getCancelButton() {
        List<WebElement> continueButton = getDriver().findElements(CancelButton);
        if(continueButton.size() == 0){
            return null;
        }
        return continueButton.get(0);
    }
    public void inputFirstName(String name){
        getDriver().findElement(FirstName).sendKeys(name);
    }

    public void inputLastName(String name){
        getDriver().findElement(LastName).sendKeys(name);
    }

    public void inputPostalCode(String code){
        getDriver().findElement(PostalCode).sendKeys(code);
    }

    public void submitForm(){
        getDriver().findElement(ContinueButton).click();
    }

    public void clearForm(){
        inputFirstName("");
        inputLastName("");
        inputPostalCode("");
        closeErrorMessage();
    }

    public void closeErrorMessage(){
        List<WebElement> errorButton = getDriver().findElements(ErrorButton);
        if(errorButton.size() == 1){
            errorButton.get(0).click();
        }
    }

    public String readErrorMessage(){
        List<WebElement> errorMessage = getDriver().findElements(ErrorMessage);
        if(errorMessage.size() == 1){
            return errorMessage.get(0).getText();
        }
        return "";
    }
}
