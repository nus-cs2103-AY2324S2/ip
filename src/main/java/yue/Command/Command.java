package yue.Command;

import yue.YueException;
import yue.Storage;
import yue.Tasks.TaskList;


/**
 * Represents a command to be executed by Duke.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage handler.
     * @throws YueException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws YueException;


    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}
