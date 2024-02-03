package commands;

import excceptions.WeiException;
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
     * @throws WeiException If the command is incomplete.
     */
    public abstract void execute(TaskList tasks, Ui ui) throws WeiException;

    /**
     * Indicates if this command is an exit command.
     *
     * @return True for exit command, false otherwise.
     */
    public abstract boolean isExit();
}
