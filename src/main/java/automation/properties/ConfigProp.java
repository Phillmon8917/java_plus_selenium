package automation.properties;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configs/config.properties")
public interface ConfigProp extends Config
{
    @Config.Key("Url")
    String url();
}
