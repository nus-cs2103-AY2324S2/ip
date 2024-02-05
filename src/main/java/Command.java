/**
 * Represents a user command to be executed
 */
public abstract class Command {
//    private boolean hasBeenExecuted = false;

    /**
     * Returns if the command is to exit. ExitCommand overrides this method to return true.
     *
     * @return true if it is an ExitCommand
     */
    public boolean isExit() {
        return false;
    }

//    public abstract String commandStatus();

    /**
     * Executes the command.
     *
     * @param tasks TaskList to be used in execution. May be modified.
     * @param storage Storage for automatic saving of changes
     */
    public abstract void execute(TaskList tasks, Storage storage);

    @Override
    public String toString() {
        return "Empty shell of command";
    }
}
