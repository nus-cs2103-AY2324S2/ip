package commands;

import exceptions.DukeException;
import tasklists.TaskList;

import java.io.IOException;

/**
 * Encapsulates an executable command
 */
public class Command {
    protected TaskList tasks;

    /**
     * Performs the series of actions declared in function.
     * @throws DukeException
     * @throws IOException
     */
    public void execute() throws DukeException, IOException {
        throw new DukeException("Invalid call of execute()");
    };

    /**
     * Sets the data.
     * @param tasks The task list containing all tasks.
     */
    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }
}
