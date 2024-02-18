package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;

/**
 * Represents a default command to be executed.
 */
public class DefaultCommand extends Command {

    private final int type;

    /**
     * Constructor for a default command.
     *
     * @param type The type of default command.
     */
    public DefaultCommand(int type) {
        this.type = type;
    }

    /**
     * Executes the default command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.type == 0) {
            ui.printErrInvalidCommand();
            return;
        }

        if (this.type == 1) {
            ui.printErrInvalidTask();
            return;
        }

        if (this.type == 2) {
            ui.printErrInvalidDate();
            return;
        }
    }
}
