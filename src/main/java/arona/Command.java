package arona;

/**
 * Abstract class which can be extended from to specify
 * type of command and how they can be executed
 */
public abstract class Command {
    protected String fullCommand;
    protected boolean exit;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Convert the command and perform the action to the task in the tasklist
     *
     * @param taskList The class that contains the tasklist
     * @param ui UI that handles all the console output
     * @param storage Handle the loading and saving of data
     * @throws FileException if file cannot be found
     * @throws TaskException if command input does not match the requirements
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws FileException, TaskException;
    public boolean isExit() {
        return this.exit;
    }
}
