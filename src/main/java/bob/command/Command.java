package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.exception.BobException;

/**
 * Represents an arbitrary action. A <code>Command</code> object corresponds to
 * an action to execute according to the command entered by the user.
 */
public abstract class Command {
    /**
     * Executes the encapsulated action.
     *
     * @param storage The storage to save the changes resulting from execution of the command.
     * @param taskList The task list to keep track of the tasks before and after executing the command.
     * @return The response as a result of executing the command.
     * @throws BobException If there is an error executing the command.
     */
    public abstract String execute(Storage storage, TaskList taskList) throws BobException;
}
