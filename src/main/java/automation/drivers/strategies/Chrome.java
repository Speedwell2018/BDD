package automation.drivers.strategies;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public class Chrome implements DriverStrategy {
        public WebDriver setStrategy() {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("useAutomationExtension", false);
           options.addArguments("--no-sandbox");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--incognito");

            return new ChromeDriver(options);
        }
    }

