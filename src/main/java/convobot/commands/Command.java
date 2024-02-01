package commands;

import exceptions.ConvoBotException;
import utils.TaskList;
import utils.UI;

/**
 * Represents a command in the ConvoBot application.
 */
public interface Command {

    /**
     * Executes the command with the given task list and user interface.
     *
     * @param taskList The task list on which the command operates.
     * @param ui       The user interface for displaying messages.
     * @throws ConvoBotException If an exception specific to ConvoBot occurs during command execution.
     */
    public void execute(TaskList taskList, UI ui) throws ConvoBotException;

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit();
}
