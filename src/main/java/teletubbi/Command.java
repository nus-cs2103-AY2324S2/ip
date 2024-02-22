package teletubbi;

/**
 * Represents a command object to be executed.
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Executes command.
     *
     * @param taskList Tasklist containing tasks.
     * @param ui User interface.
     * @param storage Storage to store task content.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean b) {
        isExit = b;
    }
}
