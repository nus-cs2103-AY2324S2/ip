package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;

/**
 * Represents a greet command to be executed.
 */
public class GreetCommand extends Command {

    /**
     * Constructor for a greet command.
     */
    public GreetCommand() {
        super();
    }

    /**
     * Executes the greet command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.greet();
    }
}
