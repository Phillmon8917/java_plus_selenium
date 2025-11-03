package automation.flow;

public class CommandInvoker
{
    public static void submit(Command command, String username, String password)
    {
        command.execute(username, password);
    }

    public static void undo(Command command)
    {
        command.undo();
    }

    private CommandInvoker()
    {
    }
}
