package automation.strategy;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DriverSingleton
{
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setDriver(String strategy)
    {
        if (driverThreadLocal.get() == null)
        {
            DriverStrategy driverStrategy = DriverStrategyImplementer.chooseStrategy(strategy);
            WebDriver driver = driverStrategy.setStrategy();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driverThreadLocal.set(driver);
        }
    }

    public static WebDriver getDriver()
    {
        return driverThreadLocal.get();
    }

    public static void closeDriver()
    {
        if (driverThreadLocal.get() != null)
        {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }

    private DriverSingleton(){}
}