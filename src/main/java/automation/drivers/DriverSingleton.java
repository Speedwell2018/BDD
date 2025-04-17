package automation.drivers;

import automation.drivers.strategies.DriverStrategy;
import automation.drivers.strategies.DriverStrategyImplementer;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DriverSingleton {
    private static DriverSingleton instance=null;
    private static WebDriver driver;


    private DriverSingleton(String driver)
    {
        instantiate(driver);
    }
    public WebDriver instantiate(String driver){
        DriverStrategy driverStrategy = DriverStrategyImplementer.chooseStrategy();
        DriverSingleton.driver = driverStrategy.setStrategy();
        DriverSingleton.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        DriverSingleton.driver.manage().window().maximize();
        return DriverSingleton.driver;
    }

    public static void getInstance(String driver){
        if(instance==null){
            instance= new DriverSingleton(driver);
        }
    }
    public static void closeObjectInstance(){
        instance=null;
        driver.quit();
    }
    public static WebDriver getDriver() {
        return driver;
    }
}
