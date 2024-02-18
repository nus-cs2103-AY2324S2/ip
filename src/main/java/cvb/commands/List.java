package cvb.commands;

import cvb.utils.ResponseConstructor;
import cvb.utils.TaskList;

/**
 * Represents a command to display the list of tasks in the ConvoBot application.
 */
public class List implements Command {

    /**
     * Executes the List command by displaying the task list using the provided user interface.
     *
     * @param taskList The task list to be displayed.
     * @param rc       The response constructor for constructing messages.
     */
    public void execute(TaskList taskList, ResponseConstructor rc) {
        rc.showTaskList(taskList);
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
