package pages;
import drivers.DriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;
import utils.Utils;

import java.time.Duration;

public class ShopPage {
    private WebDriver driver;

    public ShopPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#main > ul > li.product.type-product.post-211.status-publish> a.button")
    private WebElement addToCartButton;

    @FindBy(css = "body > nav > div.wb4wp-wrapper > div.wb4wp-right > div > a > span")
    private WebElement numberOfProducts;

    @FindBy(css = "body > nav > div.wb4wp-wrapper > div.wb4wp-right > div > a")
    private WebElement cartButton;

    @FindBy(css = "#main > nav > ul > li:nth-child(2) > a")
    private WebElement secondPage;

    @FindBy(css = "#main > nav > ul > li:nth-child(3) > a")
    private WebElement thirdPage;

    public void addElementToCart() {
        secondPage.click();
        String cart_counter=numberOfProducts.getText();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        boolean isUpdated = wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(numberOfProducts, cart_counter)));
        if(isUpdated) {
            System.out.println("Cart has been updated");
            Utils.takeScreenshot();
        }else {
            System.out.println("Cart has not been updated");
        }
    }
    public void goToThirdPage(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", thirdPage);
    }

    public String getNumberOfProducts(){
        return numberOfProducts.getText();
    }

    public void proceedToCheckout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));

        cartButton.click();
    }

}
