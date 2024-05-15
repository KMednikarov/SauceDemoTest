package org.example.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.example.context.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private By LoginForm = By.xpath("//*[@id='login_button_container']/div/form");
    private By UserName = By.xpath("//*[@id='user-name']");
    private By Password = By.xpath("//*[@id='password']");

    public LoginPage(WebDriver driver, TestContext testContext){
        super(driver, testContext, LogManager.getLogger(LoginPage.class));
    }
    public void login(String user, String password){
        WebElement loginForm = getDriver().findElement(LoginForm);
        WebElement usernameInput = loginForm.findElement(UserName);
        WebElement passwordInput = loginForm.findElement(Password);

        usernameInput.sendKeys(user);
        passwordInput.sendKeys(password);

        loginForm.submit();
        getTestContext().setLoggedIn(true);
    }
}
