package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.Tasks.TaskList;


/**
 * Represents a command to be executed by Duke.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage handler.
     * @throws DukeException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;


    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}
