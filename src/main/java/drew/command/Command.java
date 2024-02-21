package drew.command;

import drew.storage.Storage;
import drew.storage.TaskList;

/**
 * This is an abstract class representing commands.
 */
public abstract class Command {
    protected String input;

    protected Command(String input) {
        this.input = input;
    }
    /**
     * Executes the command
     * @param tasks Tasklist object that contains the tasks.
     * @param storage Storage object that handles storage related tasks.
     * @return The response from the task.
     * @throws IllegalArgumentException Thrown when the command is called with wrong arguments.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws IllegalArgumentException;
}
