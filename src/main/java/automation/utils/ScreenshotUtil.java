package automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil
{
    public static String captureScreenshotAsBase64(WebDriver driver)
    {
        try
        {
            Thread.sleep(2000);
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (Exception ex)
        {
            return null;
        }
    }

    private ScreenshotUtil()
    {
    }
}
