package automation.dataloader.login;

import lombok.Getter;

import java.util.Objects;

@Getter
public class LoginDataObject
{
    private final String scenarioName;
    private final String username;
    private final String password;
    private final boolean passwordEncoded;
    private final String expectedResult;

    private LoginDataObject(Builder builder)
    {
        this.scenarioName = builder.scenarioName;
        this.username = builder.username;
        this.password = builder.password;
        this.passwordEncoded = builder.passwordEncoded;
        this.expectedResult = builder.expectedResult;
    }

    public static class Builder
    {
        private String scenarioName;
        private String username;
        private String password;
        private boolean passwordEncoded;
        private String expectedResult;

        public Builder withScenarioName(String scenarioName)
        {
            this.scenarioName = scenarioName;
            return this;
        }

        public Builder withUsername(String username)
        {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password)
        {
            this.password = password;
            return this;
        }

        public Builder withPasswordEncoded(boolean passwordEncoded)
        {
            this.passwordEncoded = passwordEncoded;
            return this;
        }

        public Builder withExpectedResult(String expectedResult)
        {
            this.expectedResult = expectedResult;
            return this;
        }

        public LoginDataObject build()
        {
            return new LoginDataObject(this);
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginDataObject that = (LoginDataObject) o;

        return Objects.equals(scenarioName, that.scenarioName)
                && Objects.equals(username, that.username)
                && Objects.equals(password, that.password)
                && passwordEncoded == that.passwordEncoded
                && Objects.equals(expectedResult, that.expectedResult);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(scenarioName, username, password, passwordEncoded, expectedResult);
    }
}
