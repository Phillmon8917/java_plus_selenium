package automation.pages;

import automation.basepage.LoginBasePage;
import automation.utils.WaitUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoginPage extends LoginBasePage
{
    public void clickForgotPassword(String username)
    {
        try
        {
            WaitUtil.waitUntilVisible(resetPasswordLink);
            Actions actions = new Actions(driver);
            actions.scrollToElement(resetPasswordLink);
            resetPasswordLink.click();

            WaitUtil.waitUntilVisible(usernameInput);
            usernameInput.clear();
            usernameInput.sendKeys(username);

            WaitUtil.waitUntilClickable(resetButton);
            resetButton.click();

        } catch (Exception ex)
        {
            log.error("An error has occurred", ex);
        }
    }

    public boolean isResetLinkSent()
    {
        try
        {
            WaitUtil.waitUntilVisible(resetLinkSentConfirmation);
            return resetLinkSentConfirmation.isDisplayed();
        } catch (Exception ex)
        {
            return false;
        }
    }

    public boolean dashboardIsDisplayed()
    {
        try
        {
            WaitUtil.waitUntilVisible(dashboardPageTitle);
            return dashboardPageTitle.isDisplayed();
        } catch (Exception ex)
        {
            return false;
        }
    }

    public boolean isUserOnTheLoginPage()
    {
        try
        {
            WaitUtil.waitUntilClickable(loginButton);
            return loginButton.isDisplayed();
        } catch (Exception ex)
        {
            return false;
        }
    }

    private LoginPage()
    {
    }
}
