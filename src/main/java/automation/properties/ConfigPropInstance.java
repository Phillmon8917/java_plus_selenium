package automation.properties;

import org.aeonbits.owner.ConfigCache;

public class ConfigPropInstance
{
    public static ConfigProp getConfig()
    {
        return ConfigCache.getOrCreate(ConfigProp.class);
    }

    private ConfigPropInstance()
    {
    }
}
