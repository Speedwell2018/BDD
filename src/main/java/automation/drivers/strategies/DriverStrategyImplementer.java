package automation.drivers.strategies;
import automation.utils.Constants;

public class DriverStrategyImplementer {
    public static DriverStrategy chooseStrategy(){
       /* switch(strategy){
            case Constants.CHROME:
                return new Chrome();

            case Constants.FIREFOX:
                return new FireFox();

            default:
                return Chrome();
        }*/
        return new Chrome();

    }
}
