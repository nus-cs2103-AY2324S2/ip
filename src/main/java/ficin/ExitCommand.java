package ficin;

import ficin.task.TaskList;

/**
 * Represents a command to exit the Duke application. This command handles any necessary
 * finalization before the application shuts down, such as saving the current state of the task list.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by performing necessary cleanup actions such as saving tasks
     * and displaying a goodbye message. This method ensures the application is closed gracefully.
     *
     * @param taskList  The task list, which may be saved to persistent storage as part of exit processing.
     * @param ui        The user interface for displaying the goodbye message and closing any resources.
     * @param userInput The user input leading to the execution of this command (not directly used here).
     * @throws DukeException If there is an issue during the execution of the command, such as an error
     *                       saving tasks to storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException {
        try {
            Storage.saveTasks(taskList.getTasks());
            ui.showGoodbye();
        } catch (Exception e) {
            throw new DukeException("Failed to save tasks before exiting: " + e.getMessage());
        } finally {
            ui.closeScanner();
        }
    }
}
