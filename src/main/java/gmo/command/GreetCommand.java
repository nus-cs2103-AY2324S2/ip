package gmo.command;

import gmo.util.Storage;
import gmo.util.TaskList;
import gmo.ui.Ui;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.greet();
    }
}
