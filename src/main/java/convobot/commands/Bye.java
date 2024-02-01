package commands;

import utils.TaskList;
import utils.UI;

/**
 * Represents a command to exit the application.
 */
public class Bye implements Command {

    /**
     * Executes the Bye command. This implementation does nothing as it signifies the end of the application.
     *
     * @param taskList The task list (not used in this command).
     * @param ui       The user interface (not used in this command).
     */
    public void execute(TaskList taskList, UI ui) {
        return;
    }

    /**
     * Checks if the Bye command is an exit command. Always returns true for Bye commands.
     *
     * @return Always true.
     */
    public boolean isExit() {
        return true;
    }
}
