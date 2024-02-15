package arona;

/**
 * Allow for user to see the tasklist.
 */
public class ListTask extends Command {
    public ListTask(String fullCommand) {
        super(fullCommand);
        this.exit = false;
    }

    /**
     * Prints the tasklist.
     *
     * @param taskList The class that contains the tasklist.
     * @param ui UI that handles all the console output.
     * @param storage Handle the loading and saving of data.
     * @throws FileException if file cannot be found.
     * @throws TaskException if command input does not match the requirements.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTasks(taskList.getTasks());
    }
}