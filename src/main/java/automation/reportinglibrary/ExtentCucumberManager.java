package automation.reportinglibrary;

import com.aventstack.extentreports.ExtentReports;

public class ExtentCucumberManager
{
    private static ExtentReports extent;

    public static ExtentReports getInstance()
    {
        if (extent == null)
        {
            extent = ExtentReportManager.getExtentReporter("TestResults");
        }
        return extent;
    }
}
