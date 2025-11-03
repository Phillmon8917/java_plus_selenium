package automation.utils;

import automation.strategy.DriverSingleton;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class WaitUtil
{
    private static final int WAIT_PERIOD_IN_SECONDS = 10;
    public static void waitUntilVisible(WebElement element)
    {
        new FluentWait<>(DriverSingleton.getDriver())
                .withTimeout(Duration.ofSeconds(WAIT_PERIOD_IN_SECONDS))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(driver -> element.isDisplayed());
    }

    public static void waitUntilClickable(WebElement element)
    {
        new FluentWait<>(DriverSingleton.getDriver())
                .withTimeout(Duration.ofSeconds(WAIT_PERIOD_IN_SECONDS))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(driver -> element.isDisplayed() && element.isEnabled());

    }

    private WaitUtil()
    {
    }
}
