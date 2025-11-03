package automation.flags;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LoginAssertionsFlag
{
    ENTER_CORRECT_CREDENTIALS("Enter correct credentials"),
    ENTER_INCORRECT_USERNAME("Enter incorrect username"),
    ENTER_INCORRECT_PASSWORD("Enter incorrect password"),
    LEAVE_USERNAME_BLANK("Leave username blank"),
    LEAVE_PASSWORD_BLANK("Leave password blank"),
    LEAVE_USERNAME_AND_PASSWORD_BLANK("Leave Username & Passowrd Blank"),
    SQL_INJECTION_ATTEMPT_IN_USERNAME("SQL injection attempt in username"),
    SPECIAL_CHARACTERS_IN_PASSWORD_FIELD("Special characters in password field"),
    REQUEST_PASSWORD_RESET_FOR_VALID_USERNAME("Request password reset for valid username"),
    REQUEST_PASSWORD_RESET_FOR_INVALID_USERNAME("Request password reset for invalid username");

    private final String value;
}
