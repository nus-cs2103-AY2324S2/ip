package alpa.commands;

import alpa.tasks.TaskList;
import alpa.ui.Ui;
import alpa.utils.Storage;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param taskList The task list containing the tasks.
     * @param ui The user interface to display the task list.
     * @param storage The storage to save the task list.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList.getTasks());
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return False, as the list command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
