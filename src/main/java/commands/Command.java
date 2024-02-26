package commands;

import exceptions.DukeException;
import storage.TaskList;
import ui.UserInterface;

/**
 * Represents a command input by the user that needs to be executed.
 */
public interface Command {

    /**
     * Performs actions based on the type of command.
     *
     * @param list TaskList of the application
     * @param ui UserInterface for outputting messages
     * @return true if application should close after execution of command, false otherwise
     */
    boolean execute(TaskList list, UserInterface ui) throws DukeException;
}
