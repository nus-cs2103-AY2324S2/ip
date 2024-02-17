package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;
import duke.utils.DukeException;

/**
 * The specification for the command classes.
 *
 * By default, the command is not an exit command.
 *
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface and storage.
     *
     * @param tasks The list of tasks
     * @param ui The user interface to write results to
     * @param storage The storage to save the tasks to
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, TextUi ui, Storage storage) throws DukeException;

    /**
     * Returns whether the command is an exit command. Only true for the ByeCommand.
     *
     * @return whether the command is an exit command
     */
    public boolean isExit() {
        return false;
    }
}

