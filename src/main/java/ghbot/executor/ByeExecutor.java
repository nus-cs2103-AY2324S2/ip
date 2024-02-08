package ghbot.executor;

/**
 * ByeExecutor Class.
 * Executes "bye" command.
 */
public class ByeExecutor extends Executor {
    private String executeStr;
    /**
     * ByeExecutor Constructor.
     */
    public ByeExecutor() {
        this.executeStr = "";
    }

    /**
     * Prints goodbye message to user.
     */
    @Override
    public String execute() {
        this.executeStr = "Bye. Hope to see you again soon!";
        return this.executeStr;
    }
}
