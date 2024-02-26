package commands;

import exceptions.CalException;
import storage.StorageManager;
import tasklist.TaskList;

/**
 * Represents a command entered by a user.
 */
public abstract class Command {
    /**
     * Execute the command. Execution will be different based on the command type.
     *
     * @param tasks          the taskList
     * @param storageManager to load and save tasks
     * @return String print output.
     * @throws CalException if there was an error when executing the command.
     */
    public abstract String execute(TaskList tasks, StorageManager storageManager) throws CalException;
    public abstract boolean isExit();
}
