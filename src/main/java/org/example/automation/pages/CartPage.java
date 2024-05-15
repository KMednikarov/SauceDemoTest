package org.example.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.example.context.TestContext;
import org.example.models.Product;
import org.example.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage{
    public static final String URL = Constants.BASE_URL + "/cart.html";
    private final By CartItem = By.className("cart_item");
    private final By CartItemName = By.xpath(".//div[contains(@data-test,'inventory-item-name')]");
    private final By CartItemDescription = By.xpath(".//div[contains(@data-test,'inventory-item-desc')]");
    private final By CartItemPrice = By.xpath(".//div[contains(@data-test,'inventory-item-price')]");

    public CartPage(WebDriver driver, TestContext testContext) {
        super(driver, testContext, LogManager.getLogger(CartPage.class));
    }

    public List<Product> getCartProducts(){
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

    public void navigate() {
        super.navigate(CartPage.URL);
    }
}
