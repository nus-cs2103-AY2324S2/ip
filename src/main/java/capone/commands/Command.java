package capone.commands;

import capone.exceptions.CaponeException;
import capone.TaskList;
import capone.Storage;
import capone.Ui;

/**
 * An abstract class representing a command in the Capone application.
 * All concrete command classes must extend this class and implement the execute method.
 *
 * @author Tay Rui-Jie
 */
public abstract class Command {

    /**
     * Executes the command with the specified TaskList, Ui, and Storage.
     *
     * @param taskList The TaskList to be updated.
     * @param ui       The Ui to interact with the user.
     * @param storage  The Storage for saving data.
     * @throws CaponeException If any Capone-related exception occurs.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException;
}
