import drivers.DriverSingleton;


import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.Constants;
import utils.FrameworkProperties;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class Tests {

        static FrameworkProperties frameworkProperties;
        static WebDriver driver;
        static HomePage homePage;
        static SignInPage signInPage;
        static CheckoutPage checkoutPage;
        static ShopPage shopPage;
        static CartPage cartPage;

        @BeforeAll
        public static void initializeObjects(){
            frameworkProperties = new FrameworkProperties();
            DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
            driver = DriverSingleton.getDriver();
            homePage = new HomePage();
            signInPage = new SignInPage();
            shopPage = new ShopPage();
            checkoutPage = new CheckoutPage();
            cartPage = new CartPage();
        }

    @Test
    public void testing1Authentication(){
        driver.get(Constants.URL);
        homePage.clickSignIn();
        signInPage.logIn(frameworkProperties.getProperty(Constants.EMAIL), frameworkProperties.getProperty(Constants.PASSWORD));
        assertEquals(frameworkProperties.getProperty(Constants.USERNAME), homePage.getUsername());
    }

        @ParameterizedTest
        @CsvSource({
                "laurentiu@bitheap.tech, MTIzNDU2",
                "secondtest, MTIzNDU2"
                })
        public void testingParamScenario(String username, String password){
            driver.get(Constants.URL);
            homePage.clickSignIn();
            signInPage.logIn(username, password);
            assertTrue(homePage.getUsername().contains("Hello"));

        }

        @Test
        public void testing2AddingThingsToCart(){
            driver.get(Constants.URL);
            homePage.clickShopButton();
            //shopPage.goToThirdPage();
            shopPage.addElementToCart();
            assertEquals(Constants.CART_QUANTITY, shopPage.getNumberOfProducts());
        }

        @Test
        public void testing3TheFullBuyingProcess(){
            driver.get(Constants.URL);
            homePage.clickShopButton();
            //shopPage.goToThirdPage();
            shopPage.addElementToCart();
            shopPage.proceedToCheckout();
            cartPage.proceedToCheckout();
            //checkoutPage.provideBillingDetails();
            //checkoutPage.providePersonalDetails();
            checkoutPage.placeOrder();
            assertTrue(checkoutPage.getOrderStatus());
        }

     @AfterAll
        public static void closeObjects(){
            driver.close();
        }


}
