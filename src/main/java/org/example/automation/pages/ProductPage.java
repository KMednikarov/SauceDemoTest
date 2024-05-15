package org.example.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.example.models.InventoryItem;
import org.example.context.TestContext;
import org.example.utils.Constants;
import org.example.web.XPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ProductPage extends BasePage{
    public static final String URL = Constants.BASE_URL + "/inventory.html";
    public ProductPage(WebDriver driver, TestContext testContext){
        super(driver, testContext, LogManager.getLogger(LoginPage.class));
    }

    public void addItemsToCart(int count){
        List<WebElement> inventoryItems = getDriver().findElements(By.className("inventory_item"));

        for (WebElement item : inventoryItems) {
            String itemName = item.findElement(By.xpath(XPaths.ITEM_NAME)).getText();
            String itemDescription = item.findElement(By.xpath(XPaths.ITEM_DESCRIPTION)).getText();
            String itemPrice = item.findElement(By.xpath(XPaths.ITEM_PRICE)).getText();

            WebElement addToCartButton = item.findElement(By.xpath(XPaths.ADD_TO_CART_BTN));
            addToCartButton.click();
            getTestContext().addItemToCart(new InventoryItem(itemName, itemDescription, itemPrice));
            count--;

            if(count == 0) break;
        }
    }

    public void removeItemsFromCart(List<InventoryItem> itemsToRemove) {
        List<WebElement> inventoryItems = getDriver().findElements(By.className("inventory_item"));
        for (InventoryItem itemToRemove : itemsToRemove) {
            for (WebElement item : inventoryItems) {
                String itemName = item.findElement(By.xpath(XPaths.ITEM_NAME)).getText();
                if (!itemName.equalsIgnoreCase(itemToRemove.name()))
                    continue;

                WebElement removeButton = item.findElement(By.xpath(XPaths.REMOVE_FROM_CART_BTN));
                removeButton.click();
            }
        }
        getTestContext().getCart().getCartItems().removeAll(itemsToRemove);
    }

    public int getCartBadgeCount() {
        List<WebElement> cartBadge = getDriver().findElements(By.xpath("//*[@id='shopping_cart_container']/a/span"));
        if(cartBadge.isEmpty())
            return 0;
        return Integer.parseInt(cartBadge.get(0).getText());
    }
}
