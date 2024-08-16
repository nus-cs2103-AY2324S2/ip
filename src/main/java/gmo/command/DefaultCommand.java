package gmo.command;

import gmo.ui.Ui;
import gmo.util.Storage;
import gmo.util.TaskList;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.type == 0) {
            return ui.printErrInvalidCommand();
        }

        if (this.type == 1) {
            return ui.printErrInvalidTask();
        }

        if (this.type == 2) {
            return ui.printErrInvalidDate();
        }

        return "Error: invalid default command type.";
    }
}
