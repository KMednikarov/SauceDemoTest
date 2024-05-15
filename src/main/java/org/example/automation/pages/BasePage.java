package org.example.automation.pages;

import org.apache.logging.log4j.Logger;
import org.example.context.TestContext;
import org.openqa.selenium.WebDriver;

public class BasePage {
    public Logger LOG;
    private WebDriver driver;
    private TestContext testContext;

    public BasePage(WebDriver driver, TestContext testContext, Logger logger){
        this.driver = driver;
        this.testContext = testContext;
        this.LOG = logger;
    }

    protected WebDriver getDriver(){
        return this.driver;
    }

    public void disconnect(){
        if(driver != null){
            driver.quit();
        }
    }
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public TestContext getTestContext(){
        return this.testContext;
    }
}
