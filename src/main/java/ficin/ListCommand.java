package ficin;

import ficin.task.TaskList;

/**
 * Represents a command to list all tasks currently stored in the task list.
 * This class extends the Command class, providing specific behavior to enumerate
 * all tasks and display them to the user through the provided UI interface.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, retrieving all tasks from the provided TaskList
     * and displaying them via the UI interface. This method allows users to view
     * a summary of all tasks currently tracked by the application.
     *
     * @param taskList  The TaskList from which to retrieve and display tasks.
     * @param ui        The Ui interface used for displaying the list of tasks to the user.
     * @param userInput The user input that triggered this command.
     *                  It is not used here, but required by the method signature).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String userInput) {
        if (taskList.getSize() == 0) {
            ui.showNoTask();
        } else {
            ui.showTasks(taskList.getTasks());
        }
    }
}
