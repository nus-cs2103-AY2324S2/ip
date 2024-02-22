package command;

import exception.BuddyException;
import storage.Storage;
import task.TaskList;


/**
 * Represents Commands to be executed by buddy.Buddy.
 */
public abstract class Command {
    /**
     * Executes command depending on command type.
     *
     * @param tasks TaskList consisting of Tasks.
     * @param storage Current Storage object used.
     * @return string response representing action taken.
     * @throws BuddyException
     */
    public abstract String execute(TaskList tasks, Storage storage) throws BuddyException;

    /**
     * Returns boolean representing whether buddy.Buddy should exit.
     *
     * @return Boolean representing if buddy.Buddy should exit.
     */
    public abstract boolean isExit();
}
