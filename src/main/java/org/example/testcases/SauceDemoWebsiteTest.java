package org.example.testcases;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.automation.pages.*;
import org.example.automation.validators.*;
import org.example.exceptions.WebDriverNotFoundException;
import org.example.context.TestContext;
import org.example.models.Product;
import org.example.utils.Constants;
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

    // Pages
    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutTwoPage checkoutTwoPage;
    private CheckoutOnePage checkoutOnePage;
    private CheckoutCompletePage checkoutCompletePage;

    // Validators
    private LoginPageValidator loginPageValidator;
    private ProductPageValidator productPageValidator;
    private CartPageValidator cartPageValidator;
    private CheckoutOnePageValidator checkoutOnePageValidator;
    private CheckoutTwoPageValidator checkoutTwoPageValidator;
    private CheckoutCompletePageValidator checkoutCompletePageValidator;

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
        checkoutOnePage = new CheckoutOnePage(driver, testContext);
        checkoutTwoPage = new CheckoutTwoPage(driver, testContext);
        checkoutCompletePage = new CheckoutCompletePage(driver, testContext);
    }

    private void setupValidators() {
        loginPageValidator = new LoginPageValidator(loginPage);
        productPageValidator = new ProductPageValidator(productPage);
        cartPageValidator = new CartPageValidator(cartPage);
        checkoutOnePageValidator = new CheckoutOnePageValidator(checkoutOnePage);
        checkoutTwoPageValidator = new CheckoutTwoPageValidator(checkoutTwoPage);
        checkoutCompletePageValidator = new CheckoutCompletePageValidator(checkoutCompletePage);
    }

    @AfterClass
    public void tearDown(){
        checkoutCompletePage.logOut();
        driver.close();
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

    @Test(priority = 2, description = "Verify products are added to the cart"
            , dependsOnMethods = "verifyProductPageOpened")
    public void verifyProductsAddedToCart(){
        productPage.addItemsToCart(2);
        productPageValidator.validateProductsAddedToCart();
    }

    @Test(priority = 3, description = "Verify products are removed from the cart"
            , dependsOnMethods = "verifyProductsAddedToCart")
    public void verifyProductsRemovedFromCart(){
        List<Product> itemsToRemove = productPage.getTestContext().getCart().getCartProducts();
        productPage.removeItemsFromCart(itemsToRemove);
        productPageValidator.validateProductsRemovedFromCart();
    }

    @Test(priority = 4, description = "Verify products exist in cart page"
            , dependsOnMethods = "verifyProductsAddedToCart")
    public void verifyCartProducts(){
        productPage.addItemsToCart(2);
        productPageValidator.validateProductsAddedToCart();
        cartPage.navigate();
        List<Product> cartProducts = cartPage.getCartProducts();
        List<Product> addedProducts = testContext.getCart().getCartProducts();
        cartPageValidator.validateCartProducts(cartProducts, addedProducts);
        cartPage.checkout();
    }

    @Test(priority = 5, description = "Verify checkout information form buttons"
            , dependsOnMethods = "verifyCartProducts")
    public void verifyCheckoutInformationFormButtons(){
        checkoutOnePageValidator.validateContinueButton();
        checkoutOnePageValidator.validateCancelButton();
    }

    @Test(priority = 6, description = "Verify checkout information form"
            , dependsOnMethods = "verifyCartProducts")
    public void verifyCheckoutInformationForm() throws InterruptedException {
        checkoutOnePageValidator.validateEmptyForm();
        Thread.sleep(2000);
        checkoutOnePageValidator.validateLastNameErrorMessage();
        Thread.sleep(2000);
        checkoutOnePageValidator.validatePostalCodeErrorMessage();

        checkoutOnePage.inputFirstName("Sauce");
        checkoutOnePage.inputLastName("Demo");
        checkoutOnePage.inputPostalCode("1000");
        checkoutOnePage.submitForm();
    }

    @Test(priority = 7, description = "Verify checkout product page"
            , dependsOnMethods = "verifyCheckoutInformationForm")
    public void verifyCheckoutProductPage(){
        checkoutTwoPageValidator.validateProducts(testContext.getCart().getCartProducts());
        checkoutTwoPageValidator.validateSubTotalPrice();
        checkoutTwoPageValidator.validateTaxPrice();
        checkoutTwoPageValidator.validateTotalPrice();
        checkoutTwoPage.finish();
    }

    @Test(priority = 8, description = "Verify checkout product page"
            , dependsOnMethods = "verifyCheckoutProductPage")
    public void verifyCheckoutSuccessMessage(){
        checkoutCompletePageValidator.validateCheckoutSuccessMessage();
        checkoutCompletePage.goBackToHome();
    }
}
