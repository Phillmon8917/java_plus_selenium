package automation.basetest;

public interface TestLifecycle
{
    void initializeDriver();

    void initializeReporting();

    void cleanup();
}
