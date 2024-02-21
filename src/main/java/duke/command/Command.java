package duke.command;

import duke.TaroException;
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
     * @throws TaroException if there is an error writing to the file
     */
    public abstract String execute(TaskList tasks, Storage storage) throws TaroException;
}
