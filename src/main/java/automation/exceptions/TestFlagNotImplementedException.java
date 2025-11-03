package automation.exceptions;

public class TestFlagNotImplementedException extends RuntimeException
{
    public TestFlagNotImplementedException(String testName)
    {
        super("Test flag not implemented for: " + testName);
    }
}