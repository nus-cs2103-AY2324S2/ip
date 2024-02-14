package emisCommand;

import emis.TaskList;
import emis.Ui;
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
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage);

    /**
     * Checks if the command is an exit command.
     * @return true if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}