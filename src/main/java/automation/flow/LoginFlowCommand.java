package automation.flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginFlowCommand implements Command
{
    @Autowired
    private LoginFlow loginFlow;

    @Override
    public void execute(String username, String password)
    {
        loginFlow.logInToOrangeHRMApp(username, password);
    }

    @Override
    public void undo()
    {
        loginFlow.logOutOfOrangeHRMApp();
    }

    @Override
    public void redo(String username, String password)
    {
        execute(username, password);
    }
}
