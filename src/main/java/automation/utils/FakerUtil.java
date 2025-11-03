package automation.utils;

import com.github.javafaker.Faker;

public class FakerUtil
{
    private static final Faker faker = new Faker();

    public static String randomUsername()
    {
        return faker.name().username().replaceAll("\\W", "");
    }

    public static String randomPassword()
    {
        return faker.internet().password(8, 16, true, true, true);
    }
}
