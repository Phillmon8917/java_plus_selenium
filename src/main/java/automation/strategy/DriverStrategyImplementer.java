package automation.strategy;

import automation.constants.DriverOption;

public class DriverStrategyImplementer
{
    public static DriverStrategy chooseStrategy(String browser)
    {
        return switch (browser)
        {
            case DriverOption.CHROME -> new Chrome();
            case DriverOption.PHANTOM_JS -> new PhantomJs();
            case DriverOption.FIREFOX -> new Firefox();
            default -> null;
        };
    }
}
