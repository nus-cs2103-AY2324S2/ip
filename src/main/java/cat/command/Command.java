package cat.command;

import cat.Storage;
import cat.TaskList;
import cat.ui.response.Response;

/**
 * An abstraction for the commands of the program.
 */
public abstract class Command {
    /**
     * Executes the command. The parameters are used depending on the particular subclass.
     * @param tasks list of tasks to execute on
     * @param storage file storage interface
     */
    public abstract Response execute(TaskList tasks, Storage storage);
}
