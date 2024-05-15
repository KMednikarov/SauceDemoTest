package org.example.testcases;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.automation.pages.LoginPage;
import org.example.automation.validators.LoginPageValidator;
import org.example.automation.validators.ProductPageValidator;
import org.example.exceptions.WebDriverNotFoundException;
import org.example.context.TestContext;
import org.example.models.InventoryItem;
import org.example.utils.Constants;
import org.example.automation.pages.ProductPage;
import org.example.utils.webdriver.DriverType;
import org.example.utils.webdriver.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

@Test(testName = "Sauce Demo Website Test")
public class SauceDemoWebsiteTest extends BaseTest{
    public static final Logger LOG = LogManager.getLogger(SauceDemoWebsiteTest.class);
    private WebDriver driver;
    private TestContext testContext;
    private LoginPage loginPage;
    private LoginPageValidator loginPageValidator;
    private ProductPageValidator productPageValidator;
    private ProductPage productPage;

    //////////////////////////////////////
    // Setup
    //////////////////////////////////////

    @BeforeClass
    public void setup(){
        initializeDriver(DriverType.CHROME_DRIVER);
        driver.get(Constants.BASE_URL);
        initializePages(driver, testContext);
        initializeValidators();
    }

    private void initializeValidators() {
        loginPageValidator = new LoginPageValidator(loginPage);
        productPageValidator = new ProductPageValidator(productPage);
    }

    private void initializePages(WebDriver driver, TestContext testContext) {
        loginPage = new LoginPage(driver, testContext);
        productPage = new ProductPage(driver, testContext);
    }

    private void initializeDriver(DriverType type) {
        try {
            driver = WebDriverUtil.getWebDriver(type);
        } catch (WebDriverNotFoundException e) {
            LOG.error("Web Driver type not found.");
        }
    }
    @AfterClass
    public void tearDown(){
        productPage.disconnect();
    }

    //////////////////////////////////////
    // Tests
    //////////////////////////////////////

    @Test(priority = 1, description = "Verify Products page is opened after login")
    public void verifyProductPageOpened(){
        loginPage.login("standard_user", "secret_sauce");
        String url = loginPage.getCurrentUrl();
        Assert.assertEquals(url, Constants.PRODUCTS_PAGE);
    }

    @Test(priority = 2
            , description = "Verify products are added to the cart"
            , dependsOnMethods = "verifyProductPageOpened")
    public void verifyProductsAddedToCart(){
        productPage.addItemsToCart(2);
        int badgeCount = productPage.getCartBadgeCount();
        Assert.assertEquals(badgeCount, 2);
    }

    @Test(priority = 3, description = "Verify products are removed from the cart")
    public void verifyProductsRemovedFromCart(){
        List<InventoryItem> itemsToRemove = productPage.getTestContext().getCart().getCartItems();
        productPage.removeItemsFromCart(itemsToRemove);
        int badgeCount = productPage.getCartBadgeCount();
        Assert.assertEquals(badgeCount, 0);
    }
}
