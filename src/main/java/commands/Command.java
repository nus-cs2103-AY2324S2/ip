package commands;

import storage.TaskList;
import ui.UserInterface;

/**
 * Represents a command input by the user that needs to be executed
 */
public interface Command {

    /**
     * Perform actions based on the type of command
     * @param list TaskList of the application
     * @param ui UserInterface for outputting messages
     * @return true if ExitCommand, false otherwise
     */
    boolean execute(TaskList list, UserInterface ui);
}
