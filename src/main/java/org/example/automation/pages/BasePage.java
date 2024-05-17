package org.example.automation.pages;

import org.apache.logging.log4j.Logger;
import org.example.context.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public Logger LOG;
    private WebDriver driver;
    private TestContext testContext;
    private By LogOutButton = By.xpath("//a[@data-test='logout-sidebar-link']");
    private By OpenMenu = By.id("react-burger-menu-btn");

    public BasePage(WebDriver driver, TestContext testContext, Logger logger){
        this.driver = driver;
        this.testContext = testContext;
        this.LOG = logger;
    }

    protected WebDriver getDriver(){
        return this.driver;
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public TestContext getTestContext(){
        return this.testContext;
    }

    public void logOut(){
        openMenu();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(LogOutButton));
        getDriver().findElement(LogOutButton).click();
    }

    public void openMenu(){
        getDriver().findElement(OpenMenu).click();
    }
}
