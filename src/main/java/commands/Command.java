package commands;

import TaskList.TaskList;
import exceptions.LoadCacheException;
import storage.Storage;

import java.io.IOException;

/**
 * Represents a command to be executed.
 * A <code>Command</code> object corresponds to a command with a description and a deadline
 * e.g., <code>"deadline return book /by 2020-12-12 1800"</code>
 */
public abstract class Command {

    protected TaskList taskList;
    protected Storage storage;

    /**
     * Sets the task list to be used by the command.
     * @param taskList the task list to be used by the command
     */
    public void setData(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Executes the command to be executed.
     * @return a string representing the result of executing the command
     * @throws IOException if an I/O error occurs
     */
    abstract public String execute() throws IOException, Exceptions.InvalidInputException, LoadCacheException;
}
