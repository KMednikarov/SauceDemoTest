package org.example.automation.pages;

import org.apache.logging.log4j.Logger;
import org.example.context.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOnePage extends BasePage{
    By FirstName = By.xpath("//*[@id='first-name']");
    By LastName = By.xpath("//*[@id='last-name']");
    By PostalCode = By.xpath("//*[@id='postal-code']");
    By SubmitButton = By.xpath("//*[@id='continue']");
    By ErrorMessage = By.xpath("//*[@id='checkout_info_container']/div/form/div[1]/div[4]/h3");

    public CheckoutOnePage(WebDriver driver, TestContext testContext, Logger logger) {
        super(driver, testContext, logger);
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
        getDriver().findElement(SubmitButton).click();
    }

    public String readErrorMessage(){
        return getDriver().findElement(ErrorMessage).getText();
    }
}
