package org.example.testcases;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.automation.pages.CartPage;
import org.example.automation.pages.LoginPage;
import org.example.automation.validators.CartPageValidator;
import org.example.automation.validators.LoginPageValidator;
import org.example.automation.validators.ProductPageValidator;
import org.example.exceptions.WebDriverNotFoundException;
import org.example.context.TestContext;
import org.example.models.Product;
import org.example.utils.Constants;
import org.example.automation.pages.ProductPage;
import org.example.utils.webdriver.DriverType;
import org.example.utils.webdriver.WebDriverUtil;
import org.openqa.selenium.WebDriver;
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
    private CartPage cartPage;
    private CartPageValidator cartPageValidator;

    //////////////////////////////////////
    // Setup
    //////////////////////////////////////

    @BeforeClass
    public void setup(){
        testContext = new TestContext();
        setupDriver(DriverType.CHROME_DRIVER);
        driver.get(Constants.BASE_URL);
        setupPages(driver, testContext);
        setupValidators();
    }

    private void setupDriver(DriverType type) {
        try {
            driver = WebDriverUtil.getWebDriver(type);
        } catch (WebDriverNotFoundException e) {
            LOG.error("Web Driver type not found.");
        }
    }

    private void setupPages(WebDriver driver, TestContext testContext) {
        loginPage = new LoginPage(driver, testContext);
        productPage = new ProductPage(driver, testContext);
        cartPage = new CartPage(driver, testContext);
    }

    private void setupValidators() {
        loginPageValidator = new LoginPageValidator(loginPage);
        productPageValidator = new ProductPageValidator(productPage);
        cartPageValidator = new CartPageValidator(cartPage);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    //////////////////////////////////////
    // Tests
    //////////////////////////////////////

    @Test(priority = 1, description = "Verify Products page is opened after login")
    public void verifyProductPageOpened(){
        loginPage.login("standard_user", "secret_sauce");
        loginPageValidator.validateRedirectingToProductPage();
    }

    @Test(priority = 2
            , description = "Verify products are added to the cart"
            , dependsOnMethods = "verifyProductPageOpened")
    public void verifyProductsAddedToCart(){
        productPage.addItemsToCart(2);
        productPageValidator.validateProductsAddedToCart();
    }

    @Test(priority = 3, description = "Verify products are removed from the cart")
    public void verifyProductsRemovedFromCart(){
        List<Product> itemsToRemove = productPage.getTestContext().getCart().getCartProducts();
        productPage.removeItemsFromCart(itemsToRemove);
        productPageValidator.validateProductsRemovedFromCart();
    }

    @Test(priority = 4, description = "Verify products exist in cart page")
    public void verifyCartProducts(){
        productPage.addItemsToCart(2);
        productPageValidator.validateProductsAddedToCart();
        cartPage.navigate();
        List<Product> cartProducts = cartPage.getCartProducts();
        List<Product> addedProducts = testContext.getCart().getCartProducts();
        cartPageValidator.validateCartProducts(cartProducts, addedProducts);
    }
}
