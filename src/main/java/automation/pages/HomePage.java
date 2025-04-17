package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    public HomePage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#menu-item-2330 > a")
    private WebElement signInButton;
    @FindBy(css = "#menu-item-2332 > a")
    private WebElement logoutButton;

    @FindBy(css = "#menu-item-1310 > a")
    private WebElement shopButton;

    @FindBy(css = "#menu-item-2333 > a")
    private WebElement username;

    public void skipCookies(){
        ((JavascriptExecutor) driver).executeScript(
                "let banner = document.querySelector('.cookie-banner, #cookie-popup, .cookie-consent');" +
                        "if(banner) banner.remove();"
        );
    }

    public void clickSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
      try{
          wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();}
      catch (Exception e){
            wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
            logoutButton.click();
          wait.until(ExpectedConditions.elementToBeClickable(signInButton));
          signInButton.click();
        }

    }
    public void clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }


    public void clickShopButton() {
        skipCookies();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(shopButton));
        shopButton.click();
    }

    public String getUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(username));
        System.out.println("Username is found");
        return username.getText();
    }
}
