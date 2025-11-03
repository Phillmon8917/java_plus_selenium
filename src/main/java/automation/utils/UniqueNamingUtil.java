package automation.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UniqueNamingUtil
{

    public static String getUniqueName()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return LocalDateTime.now().format(formatter);
    }

    private UniqueNamingUtil()
    {
    }
}
