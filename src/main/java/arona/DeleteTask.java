package arona;

/**
 * Allow for user to delete tasks from the tasklist given.
 */
public class DeleteTask extends Command {
    public DeleteTask(String fullCommand) {
        super(fullCommand);
        this.exit = false;
    }

    /**
     * Convert the command and delete the task from the tasklist.
     *
     * @param taskList The class that contains the tasklist.
     * @param ui UI that handles all the console output.
     * @param storage Handle the loading and saving of data.
     * @throws FileException if file cannot be found.
     * @throws TaskException if command input does not match the requirements.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FileException {
        int taskNum = Integer.parseInt(fullCommand.split(" ", 0)[1]);
        taskList.DeleteTask(taskNum);
        storage.save(taskList.getTasks());
    }

    /**
     * @return Check if the program should exit.
     */
    @Override
    public boolean isExit() {
        return this.exit;
    }
}