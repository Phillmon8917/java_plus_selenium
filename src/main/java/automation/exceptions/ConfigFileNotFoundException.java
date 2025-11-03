package automation.exceptions;

public class ConfigFileNotFoundException extends RuntimeException
{
    public ConfigFileNotFoundException()
    {
        super("OOPS! Config file specified in ExtentReportManager was not found");
    }
}
