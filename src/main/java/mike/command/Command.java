package mike.command;

import mike.MikeException;
import mike.TaskList;

/**
 * Base class for all commands.
 * Represents common attributes shared among different types of commands.
 * @author ningc
 */
public abstract class Command {

    // todo: make execute return String instead so its more easily testable.

    /**
     * Execute the command.
     * @param taskList The list of tasks to be updated.
     * @throws MikeException If command is not executed successfully then the exception is thrown.
     */
    public abstract void execute(TaskList taskList) throws MikeException;

    /**
     * Getter.
     * @return True is the command is to exit, otherwise false.
     */
    public abstract boolean isExit();

}
