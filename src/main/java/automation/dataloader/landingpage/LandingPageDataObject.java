package automation.dataloader.landingpage;

import lombok.Getter;

@Getter
public class LandingPageDataObject
{
    private final String scenario;
    private final String url;
    private final String expectedResults;

    private LandingPageDataObject(Builder builder)
    {
        this.scenario = builder.scenario;
        this.url = builder.url;
        this.expectedResults = builder.expectedResults;
    }

    public static class Builder
    {
        private String scenario;
        private String url;
        private String expectedResults;

        public Builder withScenario(String scenario)
        {
            this.scenario = scenario;
            return this;
        }

        public Builder withUrl(String url)
        {
            this.url = url;
            return this;
        }

        public Builder withExpectedResults(String expectedResults)
        {
            this.expectedResults = expectedResults;
            return this;
        }

        public LandingPageDataObject build()
        {
            return new LandingPageDataObject(this);
        }
    }
}
