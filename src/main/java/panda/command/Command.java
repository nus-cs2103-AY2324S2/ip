package panda.command;

import panda.component.*;

import panda.exception.PandaException;

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
     * Executes the command on the given TaskList, saves changes to the cache file, and generate a reply.
     * 
     * @param tlist the TaskList on which the command is executed.
     * @param cacheFile the cache file to save changes to.
     * @return the reply generated from running the command
     * @throws PandaException if an error occurs during execution.
     */
    abstract public String execute(TaskList tlist, Storage cacheFile) throws PandaException;
}

