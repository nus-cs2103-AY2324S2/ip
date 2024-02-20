package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;

/**
 * The Command class represents a command to be run.
 */
public abstract class Command {
    /**
     * Runs the specified command.
     *
     * @param taskList The TaskList object which contains all the tasks.
     * @param ui The Ui object which is responsible for printing the command message.
     * @param storage The Storage object which is responsible for saving the changes into a text file.
     * @throws DiboException If an error is detected during the command.
     */
    public abstract void run(TaskList taskList, Ui ui, Storage storage) throws DiboException;

    /**
     * Returns a boolean value to signal if the interaction with Dibo has ended.
     * The default value is false.
     */
    public boolean isBye() {
        return false;
    }

}
