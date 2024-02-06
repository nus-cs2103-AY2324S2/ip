/**
 * Represents a user command to be executed
 */
public abstract class Command {
    protected String statusMsg = "Not executed yet";

    /**
     * Returns if the command is to exit. ExitCommand overrides this method to return true.
     *
     * @return true if it is an ExitCommand
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the status message of this command.
     */
    public String status() {
        return this.statusMsg;
    }

    /**
     * Executes the command. Updates status msg accordingly.
     *
     * @param tasks TaskList to be used in execution. May be modified.
     * @param storage Storage for automatic saving of changes
     */
    public abstract void execute(TaskList tasks, Storage storage);

//    @Override
//    public String toString() {
//        return commandStatus();
//    }
}
