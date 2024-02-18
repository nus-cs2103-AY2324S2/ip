package commands;

import exception.UncleBobException;
import storage.Storage;
import task.TaskList;

/**
 * Represents the commands that Uncle Bob can execute.
 */
public abstract class Command {
    protected TaskList tasks;
    protected Storage storage;

    public Command() {}

    /**
     * Executes command.
     * To be overwritten by child classes.
     *
     * @param tasks   The TaskList representing the collection of tasks.
     * @param storage The Storage object handling storage operations.
     * @throws UncleBobException If user input is not in the correct format.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws UncleBobException;
}
