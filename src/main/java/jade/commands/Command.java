package jade.commands;

import jade.data.TaskList;
import jade.ui.Ui;
import jade.storage.Storage;
import jade.exception.JadeException;

/**
 * The <code>Command</code> object represents a user command supported by the program.
 */
public abstract class Command {

    /**
     * Executes the command with all the tasks, the program's ui, and the storage object.
     */
    public abstract void execute (TaskList tasks, Ui ui,Storage storage) throws JadeException;

    /**
     * Checks whether the program should exit.
     *
     * @return True if the program is to be exited, otherwise false.
     */
    public abstract boolean isExit();
}
