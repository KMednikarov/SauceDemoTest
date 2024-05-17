package org.example.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.context.TestContext;
import org.example.models.Product;
import org.example.utils.Constants;
import org.example.utils.ValueExtractUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckoutTwoPage extends BasePage{
    public static final String URL = Constants.BASE_URL + "/checkout-step-two.html";
    private final By CartItem = By.xpath("//div[@data-test='inventory-item']");
    private final By CartItemName = By.xpath(".//div[@data-test = 'inventory-item-name']");
    private final By CartItemDescription = By.xpath(".//div[@data-test = 'inventory-item-desc']");
    private final By CartItemPrice = By.xpath(".//div[@data-test = 'inventory-item-price']");
    private final By FinishButton = By.xpath("//button[@data-test='finish']");
    private final By SubTotalPrice = By.xpath("//div[@data-test = 'subtotal-label']");
    private final By TaxPrice = By.xpath("//div[@data-test = 'tax-label']");
    private final By TotalPrice = By.xpath("//div[@data-test = 'total-label']");
    public CheckoutTwoPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext, LogManager.getLogger(CheckoutTwoPage.class));
    }


    public List<Product> getCheckoutProducts(){
        List<WebElement> cartItems = getDriver().findElements(CartItem);
        List<Product> products = new ArrayList<>();
        for (WebElement item : cartItems) {
            String itemName = item.findElement(CartItemName).getText();
            String itemDescription = item.findElement(CartItemDescription).getText();
            String itemPrice = item.findElement(CartItemPrice).getText();

            products.add(new Product(itemName, itemDescription, itemPrice));
        }
        return products;
    }


    public double getSubTotalPrice() {
        String price = ValueExtractUtil.fetchPrice(getDriver().findElement(SubTotalPrice).getText());
        return Double.parseDouble(price);
    }

    public double getTaxPrice() {
        String price = ValueExtractUtil.fetchPrice(getDriver().findElement(TaxPrice).getText());
        return Double.parseDouble(price);
    }

    public double getTotalPrice() {
        String price = ValueExtractUtil.fetchPrice(getDriver().findElement(TotalPrice).getText());
        return Double.parseDouble(price);
    }

    public void finish() {
        getDriver().findElement(FinishButton).click();
    }
}
