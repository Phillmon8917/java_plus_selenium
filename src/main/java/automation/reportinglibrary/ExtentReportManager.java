package automation.reportinglibrary;

import automation.exceptions.ConfigFileNotFoundException;
import automation.utils.UniqueNamingUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class ExtentReportManager
{
    private static ExtentReports extentReports;

    public static ExtentReports getExtentReporter(String reportName)
    {
        reportName = reportName.replace(" ", "");

        if (extentReports == null)
        {
            ExtentSparkReporter spark = new ExtentSparkReporter("Results/" + reportName + "_" + UniqueNamingUtil.getUniqueName() + ".html");
            extentReports = new ExtentReports();

            try
            {
                File configFile = new File("configs/extent-config.xml");

                if (!configFile.exists())
                {
                    log.error("An error has occurred");
                    throw new ConfigFileNotFoundException();
                }

                spark.loadXMLConfig(configFile);

                spark.config().setDocumentTitle("ExtentReport");

                extentReports.attachReporter(spark);

            } catch (Exception ex)
            {
                log.error("An error has occurred", ex);
            }
        }

        return extentReports;
    }

    private ExtentReportManager()
    {
    }
}
