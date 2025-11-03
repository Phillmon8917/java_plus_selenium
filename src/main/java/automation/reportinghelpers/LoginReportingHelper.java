package automation.reportinghelpers;

import automation.dataloader.login.LoginDataObject;
import com.aventstack.extentreports.ExtentTest;

import java.util.Base64;

public class LoginReportingHelper
{
    public static void createAReport(ExtentTest extentTest, LoginDataObject loginDataObject)
    {
        extentTest.info("Scenario: " + loginDataObject.getScenarioName());
        extentTest.info("Username: " + loginDataObject.getUsername());

        String passwordToLog = Base64.getEncoder().encodeToString(loginDataObject.getPassword().getBytes());

        extentTest.info("Password Encoded: " + passwordToLog);
        extentTest.info("Expected Result: " + loginDataObject.getExpectedResult());
    }

    private LoginReportingHelper()
    {
    }
}
