package command;

import dook.DookException;
import dook.Storage;
import task.TaskList;

public abstract class Command {
    protected boolean isExit = false;

    /**
     * Generic Command function. Will interact with the
     * tasklist, user interface, and storage of the bot
     * depending on the type of the Command.
     *
     * @param tasks The bot Tasklist.
     * @param storage The storage interface.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DookException;
}
