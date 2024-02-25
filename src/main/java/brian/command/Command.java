package brian.command;

import brian.storage.Storage;
import brian.task.TaskList;
import brian.ui.TextUi;
import brian.utils.BrianException;

/**
 * The specification for the command classes.
 * <p>
 * By default, the command is not an exit command.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface and storage.
     *
     * @param tasks   The list of tasks
     * @param ui      The user interface to write results to
     * @param storage The storage to save the tasks to
     * @throws BrianException
     */
    public abstract void execute(TaskList tasks, TextUi ui, Storage storage) throws BrianException;

    /**
     * Returns whether the command is an exit command. Only true for the ByeCommand.
     *
     * @return whether the command is an exit command
     */
    public boolean isExit() {
        return false;
    }
}

