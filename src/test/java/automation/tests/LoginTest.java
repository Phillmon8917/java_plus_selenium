package automation.tests;

import automation.assertions.LoggingAssertion;
import automation.basetest.BaseTest;
import automation.config.RunFrameworkConfiguration;
import automation.dataloader.login.LoginDataLoader;
import automation.dataloader.login.LoginDataObject;
import automation.exceptions.NoTestDataException;
import automation.exceptions.TestFlagNotImplementedException;
import automation.flags.LoginAssertionsFlag;
import automation.flow.CommandInvoker;
import automation.properties.ConfigPropInstance;
import automation.reportinghelpers.LoginReportingHelper;
import automation.reportinglibrary.ExtentReportManager;
import automation.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Objects;

@SpringBootTest(classes = RunFrameworkConfiguration.class)
public class LoginTest extends BaseTest
{
    private List<LoginDataObject> loginDataObjects;
    @Autowired
    private LoggingAssertion loggingAssertion;

    @Override
    public void initializeReporting()
    {
        loginDataObjects = LoginDataLoader.loadData();
        extentReports = ExtentReportManager.getExtentReporter("LoginTestReport");
        softAssert = new SoftAssert();
    }

    @Test
    public void test()
    {
        if (loginDataObjects == null || loginDataObjects.isEmpty())
        {
            throw new NoTestDataException(LoginTest.class.getName());
        }

        for (LoginDataObject loginDataObject : loginDataObjects)
        {
            driver.get(ConfigPropInstance.getConfig().url());
            boolean response = false;

            String scenario = loginDataObject.getScenarioName();

            ExtentTest extentTest = extentReports.createTest(scenario);
            LoginReportingHelper.createAReport(extentTest, loginDataObject);

            if (!scenario.contains("Request"))
            {
                CommandInvoker.submit(command, loginDataObject.getUsername(), loginDataObject.getPassword());
                response = assertionHelperPasswordInputs(scenario);
            } else
            {
                String username = loginDataObject.getUsername();
                response = assertionHelperResetPassword(username, scenario);
            }

            if (response)
            {
                extentTest.pass("Scenario Passed",  MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
            } else
            {
                extentTest.fail("Scenario Failed",  MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
            }

            softAssert.assertTrue(response);

            if (loginDataObject.getExpectedResult().equalsIgnoreCase("Login is successful"))
            {
                CommandInvoker.undo(command);
            }
        }

        softAssert.assertAll();
    }

    private boolean assertionHelperPasswordInputs(String scenarioName)
    {
        if (scenarioName.equalsIgnoreCase(LoginAssertionsFlag.ENTER_CORRECT_CREDENTIALS.getValue()))
        {
            return loggingAssertion.enterCorrectCredentials();
        } else if (scenarioName.equalsIgnoreCase(LoginAssertionsFlag.ENTER_INCORRECT_USERNAME.getValue()))
        {
            return loggingAssertion.enterIncorrectUsername();
        } else if (scenarioName.equalsIgnoreCase(LoginAssertionsFlag.ENTER_INCORRECT_PASSWORD.getValue()))
        {
            return loggingAssertion.enterIncorrectPassword();
        } else if (scenarioName.equalsIgnoreCase(LoginAssertionsFlag.LEAVE_USERNAME_BLANK.getValue()))
        {
            return loggingAssertion.leaveUsernameBlank();
        } else if (scenarioName.equalsIgnoreCase(LoginAssertionsFlag.LEAVE_PASSWORD_BLANK.getValue()))
        {
            return loggingAssertion.leavePasswordBlank();
        } else if (scenarioName.equalsIgnoreCase(LoginAssertionsFlag.LEAVE_USERNAME_AND_PASSWORD_BLANK.getValue()))
        {
            return loggingAssertion.leaveUsernameAndPasswordBlank();
        } else if (scenarioName.equalsIgnoreCase(LoginAssertionsFlag.SQL_INJECTION_ATTEMPT_IN_USERNAME.getValue()))
        {
            return loggingAssertion.sqlInjectionAttemptInUsername();
        } else if (scenarioName.equalsIgnoreCase(LoginAssertionsFlag.SPECIAL_CHARACTERS_IN_PASSWORD_FIELD.getValue()))
        {
            return loggingAssertion.specialCharactersInPasswordField();
        } else
        {
            throw new TestFlagNotImplementedException(LoginTest.class.getName());
        }

    }

    private boolean assertionHelperResetPassword(String username, String scenarioName)
    {
        if (scenarioName.equalsIgnoreCase(LoginAssertionsFlag.REQUEST_PASSWORD_RESET_FOR_VALID_USERNAME.getValue()))
        {
            return loggingAssertion.requestPasswordResetForValidUsername(username);
        } else if (scenarioName.equalsIgnoreCase(LoginAssertionsFlag.REQUEST_PASSWORD_RESET_FOR_INVALID_USERNAME.getValue()))
        {
            return loggingAssertion.requestPasswordResetForInvalidUsername(username);
        } else
        {
            throw new TestFlagNotImplementedException(LoginTest.class.getName());
        }
    }
}
