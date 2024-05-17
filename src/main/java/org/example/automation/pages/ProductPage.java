package org.example.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.example.models.Product;
import org.example.context.TestContext;
import org.example.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ProductPage extends BasePage{
    public static final String URL = Constants.BASE_URL + "/inventory.html";
    private final By RemoveButton = By.xpath(".//button[contains(@data-test,'remove-sauce')]");
    private final By CartBadge = By.xpath("//*[@id='shopping_cart_container']/a/span");
    private final By InventoryItem = By.className("inventory_item");
    private final By InventoryItemName = By.xpath(".//div[@data-test = 'inventory-item-name']");
    private final By InventoryItemDescription = By.xpath(".//div[@data-test = 'inventory-item-desc']");
    private final By InventoryItemPrice = By.xpath(".//div[@data-test = 'inventory-item-price']");
    private final By AddToCartButton = By.xpath(".//button[contains(@data-test,'add-to-cart-sauce')]");

    public ProductPage(WebDriver driver, TestContext testContext){
        super(driver, testContext, LogManager.getLogger(ProductPage.class));
    }

    public void addItemsToCart(int count){
        List<WebElement> inventoryItems = getDriver().findElements(InventoryItem);

        for (WebElement item : inventoryItems) {
            String itemName = item.findElement(InventoryItemName).getText();
            String itemDescription = item.findElement(InventoryItemDescription).getText();
            String itemPrice = item.findElement(InventoryItemPrice).getText();

            WebElement addToCartButton = item.findElement(AddToCartButton);
            addToCartButton.click();
            getTestContext().addProductToCart(new Product(itemName, itemDescription, itemPrice));
            count--;

            if(count == 0) break;
        }
    }

    public void removeItemsFromCart(List<Product> itemsToRemove) {
        List<WebElement> inventoryItems = getDriver().findElements(By.className("inventory_item"));
        for (Product itemToRemove : itemsToRemove) {
            for (WebElement item : inventoryItems) {
                String itemName = item.findElement(InventoryItemName).getText();
                if (!itemName.equalsIgnoreCase(itemToRemove.name()))
                    continue;

                WebElement removeButton = item.findElement(RemoveButton);
                removeButton.click();
            }
        }
        getTestContext().getCart().getCartProducts().removeAll(itemsToRemove);
    }

    public int getCartBadgeCount() {
        List<WebElement> cartBadge = getDriver().findElements(CartBadge);
        if(cartBadge.isEmpty())
            return 0;
        return Integer.parseInt(cartBadge.get(0).getText());
    }

    public List<WebElement> getAddedProducts(){
        List<WebElement> addedProducts = getDriver().findElements(RemoveButton);
        return addedProducts;
    }
}
