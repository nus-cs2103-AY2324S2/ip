package ghbot.executor;

/**
 * ByeExecutor Class.
 * Executes "bye" instruction.
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
     * Returns a goodbye message to user.
     * @return A string containing a goodbye message.
     */
    @Override
    public String execute() {
        this.executeStr = "Bye. Hope to see you again soon!";
        return this.executeStr;
    }
}
