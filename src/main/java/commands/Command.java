package commands;

import exceptions.DukeException;
import tasklists.TaskList;

import java.io.IOException;

/**
 * A superclass that represents all commands.
 */
public class Command {
    protected TaskList tasks;

    /**
     * Excutes the command.
     *
     * @throws DukeException Throws an exception when calling execute() on this command.
     * @throws IOException Throws an exception when I/O error is caught.
     */
    public void execute() throws DukeException, IOException {
        throw new DukeException("Invalid call of execute()");
    };

    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }
}
