package paimon.command;

import paimon.ChatException;
import paimon.task.TaskList;
import paimon.util.UiHandler;


/**
 * Represents a command to list all tasks in the task list. When executed, this command
 * triggers the UI handler to display a formatted list of all tasks, providing the user
 * with an overview of their tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by invoking the UI handler's method to display
     * the list of tasks. This method provides users with a comprehensive view
     * of their current tasks, including details such as status (done or not done),
     * type (todo, deadline, event), and descriptions.
     *
     * @param tasks The task list containing the tasks to be listed.
     * @return A String to be displayed.
     * @throws ChatException Not thrown by this command but declared due to the method signature.
     */
    @Override
    public String execute(TaskList tasks) throws ChatException {
        return UiHandler.getListMessage(tasks);
    }

    /**
     * Indicates that executing this command does not signal the application to exit.
     *
     * @return false always, as this command is intended to display information and does not terminate the application.
     */

    @Override
    public boolean isExit() {
        return false;
    }
}
