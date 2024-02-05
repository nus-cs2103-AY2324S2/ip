package ghbot.executor;

/**
 * ByeExecutor Class.
 * Executes "bye" command.
 */
public class ByeExecutor extends Executor {
    /**
     * ByeExecutor Constructor.
     */
    public ByeExecutor() {

    }

    /**
     * Prints goodbye message to user.
     */
    @Override
    public void execute() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
