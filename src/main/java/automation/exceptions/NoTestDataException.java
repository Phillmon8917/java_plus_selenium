package automation.exceptions;

public class NoTestDataException extends RuntimeException
{
    public NoTestDataException(String testName)
    {
        super("No test data available for: " + testName);
    }
}