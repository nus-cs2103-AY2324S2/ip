package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.BobException;

/**
 * Represents an arbitrary action. A <code>Command</code> object corresponds to
 * an action to execute according to the command entered by the user.
 */
public abstract class Command {
    /**
     * Executes the encapsulated action.
     *
     * @param ui The UI to display the result of executing the command.
     * @param storage The storage to save the changes resulting from execution of the command.
     * @param taskList The task list to keep track of the tasks before and after executing the command.
     * @throws BobException If there is an error executing the command.
     */
    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws BobException;

    /**
     * Returns whether the encapsulated action is to exit the program.
     *
     * @return Whether the current instance corresponds to the exit command.
     */
    public boolean isExit() {
        return false;
    }
}
