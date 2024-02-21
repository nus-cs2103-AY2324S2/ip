package commands;

import exceptions.HowieException;
import tasklists.TaskList;

import java.io.IOException;

/**
 * A superclass that represents all commands.
 */
public class Command {
    protected TaskList taskList;

    /**
     * Excutes the command.
     *
     * @throws HowieException Throws an exception when calling execute() on this command.
     * @throws IOException Throws an exception when I/O error is caught.
     */
    public String executeCommand() throws HowieException, IOException {
        throw new HowieException("Invalid call of execute()");
    };

    public void setData(TaskList tasks) {
        this.taskList = tasks;
    }
}
