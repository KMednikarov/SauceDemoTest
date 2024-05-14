package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exceptions.WebDriverNotFoundException;
import org.example.utils.webdriver.DriverType;
import org.example.utils.webdriver.WebDriverUtil;
import org.example.web.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class SauceDemoWebsite {
    public static final Logger LOG = LogManager.getLogger(SauceDemoWebsite.class);
    private WebDriver driver;
    public SauceDemoWebsite(){
        initializeDriver(DriverType.CHROME_DRIVER);
    }

    private void initializeDriver(DriverType type) {
        try {
            driver = WebDriverUtil.getWebDriver(type);
        } catch (WebDriverNotFoundException e) {
            LOG.error("Web Driver type not found.");
        }
    }

    public void login(String user, String password){
        driver.get("https://www.saucedemo.com/");
        WebElement loginForm = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form"));
        WebElement usernameInput = loginForm.findElement(By.xpath("//*[@id=\"user-name\"]"));
        WebElement passwordInput = loginForm.findElement(By.xpath("//*[@id=\"password\"]"));

        usernameInput.sendKeys(user);
        passwordInput.sendKeys(password);

        loginForm.submit();
    }

    public void addItemsToCart(int count){
        List<WebElement> items = driver.findElements(By.xpath(XPaths.ADD_TO_CART_BTN));

        for (WebElement item : items) {
            item.click();
            count--;

            if(count == 0) break;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
