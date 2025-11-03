package automation.flags;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LandingPageAssertionsFlag
{
    LOGIN_PAGE_DISPLAYED("OrangeHRM login page should be displayed"),
    INCORRECT_URL("Error page should load"),
    HTTP_TO_HTTPS("Browser should redirect to HTTPS login page");

    private final String value;
}
