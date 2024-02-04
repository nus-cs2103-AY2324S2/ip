package panda.command;
import panda.exception.PandaException;
import panda.component.*;

/**
 * Abstract base class for commands.
 */
public abstract class Command {

    /**
     * Checks if the command is an exit command.
     * 
     * @return true if the command is an exit command, false otherwise.
     */
    abstract public boolean isExit();

    /**
     * Executes the command on the given TaskList.
     * 
     * @param tlist the TaskList on which the command is executed.
     * @throws PandaException if an error occurs during execution.
     */
    abstract public void execute(TaskList tlist) throws PandaException;

    /**
     * Executes the command on the given TaskList, updates the UI, and saves changes to the cache file.
     * 
     * @param tlist the TaskList on which the command is executed.
     * @param ui the UI to update after execution.
     * @param cacheFile the cache file to save changes to.
     * @throws PandaException if an error occurs during execution.
     */
    abstract public void execute(TaskList tlist, Ui ui, Storage cacheFile) throws PandaException;
}

