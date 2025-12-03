package automation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * This class tells spring boot to scan from what level in this case,
 * withing the scope of automation package, not anything outside.
 */
@Configuration
@ComponentScan("automation")
public class RunFrameworkConfiguration
{
    public RunFrameworkConfiguration()
    {
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
