package mike.command;

import mike.MikeException;
import mike.Storage;
import mike.TaskList;

/**
 * Base class for all commands.
 * Represents common attributes shared among different types of commands.
 * @author ningc
 */
public abstract class Command {

    /**
     * Execute the command.
     * @param taskList The list of tasks to be updated.
     * @throws MikeException If command is not executed successfully then the exception is thrown.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws MikeException;

    /**
     * Getter.
     * @return True is the command is to exit, otherwise false.
     */
    public abstract boolean isExit();

}
