package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;

/**
 * The <code>Command</code> object represents a user command supported by the program.
 */
public abstract class Command {

    /**
     * Returns the result string, i.e. the response from Jade by executing
     * the command with all the tasks, the program's ui, and the storage object.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws JadeException;

    /**
     * Checks whether the program should exit.
     *
     * @return True if the program is to be exited, otherwise false.
     */
    public abstract boolean isExit();
}
