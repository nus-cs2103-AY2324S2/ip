package nollid.commands;

import nollid.Storage;
import nollid.TaskList;
import nollid.Ui;
import nollid.exceptions.NollidException;


/**
 * Command class is an abstract class representing an executable command.
 * It defines the execute method that subclasses must implement to perform specific command logic.
 */
public abstract class Command {
    /**
     * Abstract method to be implemented by subclasses.
     * Executes the command with specified parameters.
     *
     * @param tasks   The TaskList containing tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for data storage operations.
     * @throws NollidException Thrown if an exception specific to command execution occurs.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws NollidException;
}
