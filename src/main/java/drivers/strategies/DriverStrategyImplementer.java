package drivers.strategies;
import utils.Constants;

public class DriverStrategyImplementer {
    public static DriverStrategy chooseStrategy(String strategy){
        switch(strategy){
            case Constants.CHROME:
                return new Chrome();

            case Constants.FIREFOX:
                return new FireFox();

            default:
                return null;
        }

    }
}
