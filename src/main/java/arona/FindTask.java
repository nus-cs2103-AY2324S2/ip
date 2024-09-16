package arona;

/**
 * Allow user to find tasks with matching description
 */
public class FindTask extends Command {
    public FindTask(String fullCommand) {
        super(fullCommand);
        this.exit = false;
    }

    /**
     * Convert the command and find tasks with matching description.
     *
     * @param taskList The class that contains the tasklist.
     * @param ui UI that handles all the console output.
     * @param storage Handle the loading and saving of data.
     * @throws FileException if file cannot be found.
     * @throws TaskException if command input does not match the requirements.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FileException, TaskException {
        taskList.findTasks(fullCommand);
    }
}
