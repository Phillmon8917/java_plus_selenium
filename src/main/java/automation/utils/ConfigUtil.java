package automation.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigUtil
{

    private static final Dotenv dotenv = initDotenv();

    private static Dotenv initDotenv()
    {
        try
        {
            return Dotenv.configure()
                    .ignoreIfMissing()
                    .load();
        } catch (Exception e)
        {
            return null;
        }
    }

    public static String getUrl()
    {
        return getEnv("URL");
    }

    public static String getUsername()
    {
        return getEnv("APP_USERNAME");
    }

    public static String getPassword()
    {
        return getEnv("PASSWORD");
    }

    public static String getEmailUsername()
    {
        return getEnv("EMAILUSERNAME");
    }

    public static String getEmailPassword()
    {
        return getEnv("EMAILPASSWORD");
    }

    private static String getEnv(String key)
    {
        String systemValue = System.getenv(key);
        if (systemValue != null && !systemValue.isEmpty())
        {
            return systemValue;
        }
        if (dotenv != null)
        {
            String envValue = dotenv.get(key);
            if (envValue != null && !envValue.isEmpty())
            {
                return envValue;
            }
        }
        return null;
    }
}