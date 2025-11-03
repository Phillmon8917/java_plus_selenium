package automation.tests;

import automation.basetest.BaseTest;
import automation.config.RunFrameworkConfiguration;
import automation.dataloader.landingpage.LandingPageDataLoader;
import automation.dataloader.landingpage.LandingPageDataObject;
import automation.exceptions.NoTestDataException;
import automation.exceptions.TestFlagNotImplementedException;
import automation.flags.LandingPageAssertionsFlag;
import automation.reportinghelpers.LandingPageReportingHelper;
import automation.reportinglibrary.ExtentReportManager;
import automation.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Objects;

@SpringBootTest(classes = RunFrameworkConfiguration.class)
public class LandingPageTest extends BaseTest
{
    private List<LandingPageDataObject> landingPageDataObjects;

    @Override
    public void initializeReporting()
    {
        landingPageDataObjects = LandingPageDataLoader.loadData();
        extentReports = ExtentReportManager.getExtentReporter("LandingPageFeatureTestReport");
        softAssert = new SoftAssert();
    }

    @Test
    public void testTheLandingPage()
    {
        if (landingPageDataObjects == null || landingPageDataObjects.isEmpty())
        {
            throw new NoTestDataException(LandingPageTest.class.getName());
        }

        for (LandingPageDataObject data : landingPageDataObjects)
        {
            ExtentTest extentTest = extentReports.createTest(data.getExpectedResults());
            LandingPageReportingHelper.createAReport(extentTest, data);

            try
            {
                driver.get(data.getUrl());
            } catch (Exception ex)
            {
                //Ignored for cases of unknown domain
            }

            boolean response = assertionHelper(data.getExpectedResults());
            if (response)
            {
                extentTest.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
            } else
            {
                extentTest.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
            }
        }
    }

    private boolean assertionHelper(String expectedResults)
    {
        if (expectedResults.equalsIgnoreCase(LandingPageAssertionsFlag.LOGIN_PAGE_DISPLAYED.getValue()))
        {
            return landingPageAssertion.userIsOnTheLoginPage();
        } else if (expectedResults.equalsIgnoreCase(LandingPageAssertionsFlag.INCORRECT_URL.getValue()))
        {
            return landingPageAssertion.errorPageShouldLoad();
        } else if (expectedResults.equalsIgnoreCase(LandingPageAssertionsFlag.HTTP_TO_HTTPS.getValue()))
        {
            return landingPageAssertion.httpRedirectsToHttps();
        } else
        {
            throw new TestFlagNotImplementedException(LandingPageTest.class.getName());
        }
    }
}
