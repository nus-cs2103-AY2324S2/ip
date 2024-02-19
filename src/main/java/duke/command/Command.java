package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a command.
 */
public abstract class Command {


    /**
     * Executes the command and returns the result.
     * @param tasks the list of tasks
     * @param storage the storage object
     * @throws DukeException if there is an error writing to the file
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
