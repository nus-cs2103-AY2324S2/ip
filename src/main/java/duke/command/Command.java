package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to be executed by Duke.
 * All specific command classes should extend this abstract class.
 */
public abstract class Command {

    /**
     * Executes the command based on the given parameters.
     *
     * @param tasks   The TaskList that holds the list of tasks.
     * @param ui      The Ui to interact with the user.
     * @param storage The Storage to save or load tasks from/to a file.
     * @throws DukeException If there is an error while executing the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the command is an exit command.
     */
    public abstract boolean isExit();
}
