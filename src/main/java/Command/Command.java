package Command;

import Task.TaskList;
import Dook.Ui;
import Dook.Storage;
import Dook.DookException;

public abstract class Command {
    protected boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }
    /**
     * Generic Command function. Will interact with the
     * tasklist, user interface, and storage of the bot
     * depending on the type of the Command.
     *
     * @param tasks The bot Tasklist.
     * @param ui The user interface.
     * @param storage The storage interface.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DookException;
}