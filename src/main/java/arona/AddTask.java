package arona;

/**
 * Allow for user to add tasks to the tasklist given.
 */
public class AddTask extends Command {
    public AddTask(String fullCommand) {
        super(fullCommand);
        this.exit = false;
    }

    /**
     * Convert the command and add the task into the tasklist.
     *
     * @param taskList The class that contains the tasklist.
     * @param ui UI that handles all the console output.
     * @param storage Handle the loading and saving of data.
     * @throws FileException if file cannot be found.
     * @throws TaskException if command input does not match the requirements.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FileException, TaskException {
        if (fullCommand.equals("no")) {
            taskList.discardCommand();
            return;
        }

        storage.save(taskList.getTasks());
    }
}
