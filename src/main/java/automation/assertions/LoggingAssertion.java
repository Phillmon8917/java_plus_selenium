package automation.assertions;

import automation.pages.LoginPage;
import automation.strategy.DriverSingleton;
import automation.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggingAssertion
{
    @Autowired
    private LoginPage loginPage;

    private LoggingAssertion()
    {
    }

    public boolean enterCorrectCredentials()
    {
        return loginPage.dashboardIsDisplayed();
    }


    public boolean enterIncorrectUsername()
    {
        WebElement errorMessage = DriverSingleton.getDriver().findElement(By.xpath("//p[text()='Invalid credentials']"));
        WaitUtil.waitUntilVisible(errorMessage);
        return errorMessage.isDisplayed();
    }

    public boolean enterIncorrectPassword()
    {
        return enterIncorrectUsername();
    }


    public boolean leaveUsernameBlank()
    {
        String requiredText = DriverSingleton.getDriver().findElement(By.xpath("//span[text()='Required']")).getText();
        return !requiredText.isBlank() && requiredText.trim().equalsIgnoreCase("Required");
    }

    public boolean leavePasswordBlank()
    {
        return leaveUsernameBlank();
    }

    public boolean leaveUsernameAndPasswordBlank()
    {
        List<WebElement> requiredValidations = DriverSingleton.getDriver().findElements(By.xpath("//span[text()='Required']"));
        return requiredValidations.size() == 2;
    }

    public boolean sqlInjectionAttemptInUsername()
    {
        return enterIncorrectUsername();
    }

    public boolean specialCharactersInPasswordField()
    {
        return enterIncorrectUsername();
    }

    public boolean requestPasswordResetForValidUsername(String username)
    {
        loginPage.clickForgotPassword(username);
        return loginPage.isResetLinkSent();
    }

    public boolean requestPasswordResetForInvalidUsername(String username)
    {
        loginPage.clickForgotPassword(username);
        return !loginPage.isResetLinkSent(); // Am expecting reset link not to get sent because username doesnt exist
    }
}
