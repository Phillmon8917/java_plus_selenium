package automation.basepage;

import automation.strategy.DriverSingleton;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class LoginBasePage
{
    protected WebDriver driver;

    @FindBy(xpath = "//button[text()=' Login ']")
    protected WebElement loginButton;

    @FindBy(name = "username")
    protected WebElement usernameInput;

    @FindBy(xpath = "//p[normalize-space()='Forgot your password?']")
    protected WebElement resetPasswordLink;

    @FindBy(xpath = "//h6[normalize-space()='Reset Password link sent successfully']")
    protected WebElement resetLinkSentConfirmation;

    @FindBy(xpath = "//button[text()=' Reset Password ']")
    protected WebElement resetButton;

    @FindBy(xpath = "//h6[text()='Dashboard']")
    protected WebElement dashboardPageTitle;

    @PostConstruct
    protected void init()
    {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
}
