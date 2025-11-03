package automation.reportinghelpers;

import automation.dataloader.landingpage.LandingPageDataObject;
import com.aventstack.extentreports.ExtentTest;

public class LandingPageReportingHelper
{
    public static void createAReport(ExtentTest extentTest, LandingPageDataObject landingPageDataObject)
    {
        extentTest.info("Scenario : " + landingPageDataObject.getScenario());
        extentTest.info("URL : " + landingPageDataObject.getUrl());
        extentTest.info("Expected Results : " + landingPageDataObject.getExpectedResults());
    }

    private LandingPageReportingHelper(ExtentTest extentTest)
    {

    }
}
