package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.exception.AtlasException;


/**
 * The abstract Command class represents an executable command within the Atlas application.
 * It serves as the base for all specific command types.
 */

public abstract class Command {
    protected TaskList tasks;
    protected Ui ui;
    protected Storage storage;

    /**
     * Constructs a Command with the specified TaskList, Ui, and Storage.
     *
     * @param tasks   The TaskList containing the tasks.
     * @param ui      The Ui instance for interacting with the user.
     * @param storage The Storage instance for loading and saving tasks.
     */
    public Command(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Executes the command.
     *
     * @return A string representing the output of the program.
     * @throws AtlasException If the command encounters problems during execution.
     */
    public abstract String execute() throws AtlasException;

}
