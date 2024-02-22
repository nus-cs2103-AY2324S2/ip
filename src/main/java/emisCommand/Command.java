package emisCommand;

import emis.TaskList;
import emis.Storage;

/**
 * The Command class is an abstract class representing a command in the EMIS application.
 * Each subclass of Command represents a specific type of command that can be executed by the application.
 */
public abstract class Command {

    /**
     * Executes the command with the given parameters.
     * 
     * @param tasklist The TaskList object representing the list of tasks.
     * @param ui The Ui object handling interactions with the user.
     * @param storage The Storage object handling loading and saving of tasks.
     */
    public abstract String execute(TaskList tasklist, Storage storage);
}