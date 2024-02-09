package commands;

import helpers.TaskList;
import helpers.Ui;

/**
 * The ListCommand class represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     *
     * @param ui       The user interface object.
     * @param taskList The task list object.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.listTasks(taskList); // Display the list of tasks to the user
    }
}
