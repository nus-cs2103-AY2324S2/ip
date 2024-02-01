package command;

import exception.BuddyException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents Commands to be executed by Buddy.
 */
public class Command {
    /**
     * Executes command depending on command type.
     *
     * @param tasks TaskList consisting of Tasks.
     * @param ui Current Ui object used.
     * @param storage Current Storage object used.
     * @throws BuddyException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {};

    /**
     * Returns boolean representing whether Buddy should exit.
     *
     * @return Boolean representing if Buddy should exit.
     */
    public boolean isExit() {
        return false;
    }
}
