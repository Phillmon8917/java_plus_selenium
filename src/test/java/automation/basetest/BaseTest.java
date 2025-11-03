package automation.basetest;

import automation.assertions.LandingPageAssertion;
import automation.config.RunFrameworkConfiguration;
import automation.dataloader.landingpage.LandingPageDataLoader;
import automation.dataloader.landingpage.LandingPageDataObject;
import automation.flow.Command;
import automation.reportinglibrary.ExtentReportManager;
import automation.strategy.DriverSingleton;
import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootTest(classes = RunFrameworkConfiguration.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests implements TestLifecycle
{
    protected WebDriver driver;
    protected ExtentReports extentReports;
    protected SoftAssert softAssert;

    @Autowired
    protected LandingPageAssertion landingPageAssertion;
    @Autowired
    protected Command command;

    @BeforeTest
    public void baseSetUp() throws Exception
    {
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        initializeDriver();
        initializeReporting();
    }

    @AfterTest
    public void baseTearDown()
    {
        cleanup();
    }

    @Override
    public void initializeDriver()
    {
        DriverSingleton.setDriver("Chrome");
        driver = DriverSingleton.getDriver();
    }

    @Override
    public void initializeReporting()
    {
        extentReports = ExtentReportManager.getExtentReporter("UnknownReportName");
        softAssert = new SoftAssert();
    }

    @Override
    public void cleanup()
    {
        DriverSingleton.closeDriver();
        extentReports.flush();
    }
}