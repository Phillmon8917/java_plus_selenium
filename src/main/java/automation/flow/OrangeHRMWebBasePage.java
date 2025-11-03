package automation.flow;

import automation.strategy.DriverSingleton;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

abstract class OrangeHRMWebBasePage
{
    @FindBy(name = "username")
    protected WebElement usernameInput;

    @FindBy(name = "password")
    protected WebElement passwordInput;

    @FindBy(xpath = "//button[text()=' Login ']")
    protected WebElement loginButton;

    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
    protected WebElement userDropDown;

    @FindBy(xpath = "//*[text()='Logout']")
    protected WebElement logoutOption;

    @PostConstruct
    private void init()
    {
        PageFactory.initElements(DriverSingleton.getDriver(), this);
    }
}
