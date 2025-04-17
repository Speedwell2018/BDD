package automation.glue;

import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.*;
import automation.utils.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)

public class StepDefinition {
private WebDriver driver;
private HomePage homePage;
private SignInPage signInPage;
private CheckoutPage checkoutPage;
private ShopPage shopPage;
private CartPage cartPage;
ExtentTest test;
static ExtentReports report = new ExtentReports("report/TestReport.html");

@Autowired
    ConfigurationProperties configurationProperties;


@Before
    public void initializeObjects(){
    DriverSingleton.getInstance(configurationProperties.getBrowser());
    homePage=new HomePage();
    signInPage=new SignInPage();
    checkoutPage=new CheckoutPage();
    shopPage = new ShopPage();
    cartPage = new CartPage();
    TestCases[] tests= TestCases.values();
    test=report.startTest(tests[Utils.testCount].getTestName());
    Log.getLogData(Log.class.getName());
    Log.startTest(tests[Utils.testCount].getTestName());
    Utils.testCount++;

}
@Given("^I go to theWebSite")
    public void i_go_to_WebSite(){
    driver=DriverSingleton.getDriver();
    driver.get(Constants.URL);
    test.log(LogStatus.PASS, "Navigating to"+Constants.URL);
}

@When("^I click on Sign In button")
      public void i_click_on_sign_in_button(){
      homePage.clickSignIn();
    test.log(LogStatus.PASS, "Sign in button is clicked");
}
@When("^I add one element to the cart")
    public void i_add_one_element_to_the_cart() {
        homePage.clickShopButton();
        //shopPage.goToThirdPage();
        shopPage.addElementToCart();
    test.log(LogStatus.PASS, "The element is added to the cart successfully");
    }
@And("^I specify my credentials and click Login")
    public void i_specify_my_credentials_and_click_login(){
    signInPage.logIn(configurationProperties.getEmail(),configurationProperties.getPassword());
    test.log(LogStatus.PASS, "Credentials are inserted successfully");
}

    @And("^I proceed to checkout")
    public void i_proceed_to_checkout(){
        shopPage.proceedToCheckout();
        cartPage.proceedToCheckout();
        test.log(LogStatus.PASS, "Checkout is proceed");
    }

    @And("^I confirm address, shipping, payment and final order")
    public void i_confirm_address_shipping_payment_and_final_order(){
        checkoutPage.providePersonalDetails();
        checkoutPage.provideBillingDetails();
        checkoutPage.placeOrder();
        test.log(LogStatus.PASS, "The address is added successfully");
    }

@Then("^I can log into the website")
    public void i_can_log_into_the_website(){
    if (homePage.getUsername().equals("Hello, "+configurationProperties.getUsername())){
        test.log(LogStatus.PASS, "User login successfully");
    }else{
        test.log(LogStatus.FAIL, "Login process failed");

    }
        assertEquals("Hello, "+configurationProperties.getUsername(), homePage.getUsername());
    }
    @Then("^The elements are bought")
    public void the_elements_are_bought(){
        if (checkoutPage.getOrderStatus()){
            test.log(LogStatus.PASS, "Order is received successfully");
        }else{
            test.log(LogStatus.FAIL, "Order is not received");

        }
        assertTrue(checkoutPage.getOrderStatus());
    }

    @After
    public void closeObjects(){
    report.endTest(test);
    report.flush();
    DriverSingleton.closeObjectInstance();

    }


}
