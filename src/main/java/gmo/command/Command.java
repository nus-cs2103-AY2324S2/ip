package gmo.command;

import gmo.ui.Ui;
import gmo.util.Storage;
import gmo.util.TaskList;

/**
 * Represents a command to be executed.
 */
public abstract class Command {

    private final boolean isExit;

    public Command() {
        this.isExit = false;
    }

    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isExit;
    };


}
