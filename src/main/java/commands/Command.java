package commands;

import exceptions.WeiException;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * Performs operations requested by the user.
 */
public abstract class Command {
    /**
     * Executes the command requested.
     *
     * @param tasks All the tasks of the user.
     * @param ui Gives reply to the user.
     * @return execution results.
     * @throws WeiException If the command is incomplete.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws WeiException;

    /**
     * Indicates if this command is an exit command.
     *
     * @return True for exit command, false otherwise.
     */
    public abstract boolean isExit();
}
