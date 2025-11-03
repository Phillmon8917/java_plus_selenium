package automation.assertions;

import automation.pages.LoginPage;
import automation.strategy.DriverSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LandingPageAssertion
{
    @Autowired
    private LoginPage loginPage;

    public boolean userIsOnTheLoginPage()
    {
        return loginPage.isUserOnTheLoginPage();
    }

    public boolean errorPageShouldLoad()
    {
        return !userIsOnTheLoginPage();
    }

    public boolean httpRedirectsToHttps()
    {
        String url = DriverSingleton.getDriver().getCurrentUrl();
        return url != null && url.toLowerCase().contains("https");
    }

    private LandingPageAssertion()
    {
    }
}
