package automation.flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginFlow
{
    @Autowired
    private OrangeHRMWeb orangeHRMWeb;

    public void logInToOrangeHRMApp(String username, String password)
    {
        orangeHRMWeb.login(username, password);
    }

    public void logOutOfOrangeHRMApp()
    {
        orangeHRMWeb.logOut();
    }
}
