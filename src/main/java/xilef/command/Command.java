package xilef.command;

import xilef.XilefException;
import xilef.Storage;
import xilef.Ui;
import xilef.task.TaskList;

/**
 * An {@code Command} represents an operation that modifies the state of the application.
 */
public abstract class Command {

    /**
     * Executes the command, modifying the state of the application.
     *
     * @param tasks   The {@code TaskList} to modify.
     * @param ui      The {@code Ui} to display the results of the command.
     * @param storage The {@code Storage} to save the modified state.
     * @return A string representing the result of the command execution.
     * @throws XilefException If the command cannot be executed due to an error.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws XilefException;

    /**
     * Indicates whether the command should exit the application.
     *
     * @return {@code true} if the command should exit the application, otherwise {@code false}.
     */
    public abstract boolean isExit();
}
