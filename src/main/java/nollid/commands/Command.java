package nollid.commands;

import nollid.Storage;
import nollid.TaskList;
import nollid.exceptions.NollidException;


/**
 * Command class is an abstract class representing an executable command.
 * It defines the execute method that subclasses must implement to perform specific command logic.
 */
public abstract class Command {
    /**
     * Executes the command.
     * To be implemented by subclasses.
     *
     * @param tasks   The TaskList containing tasks.
     * @param storage The Storage for data storage operations.
     * @return A response message to be sent to the user.
     * @throws NollidException Thrown if an exception specific to command execution occurs.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws NollidException;
}
