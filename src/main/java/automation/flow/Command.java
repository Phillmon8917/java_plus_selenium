package automation.flow;

public interface Command
{
    void execute(String username, String password);
    void redo(String username, String password);
    void undo();
}
