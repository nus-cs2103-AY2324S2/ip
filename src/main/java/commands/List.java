package commands;

import utils.TaskList;
import utils.UI;

/**
 * Represents a command to display the list of tasks in the ConvoBot application.
 */
public class List implements Command {

    /**
     * Executes the List command by displaying the task list using the provided user interface.
     *
     * @param taskList The task list to be displayed.
     * @param ui       The user interface for displaying messages.
     */
    public void execute(TaskList taskList, UI ui) {
        ui.showTaskList(taskList);
    }

    /**
     * Checks if the List command is an exit command. Always returns false for List commands.
     *
     * @return Always false.
     */
    public boolean isExit() {
        return false;
    }
}
