package damon.command;

import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;

/**
 * Represents AddCommand object which is corresponding to repeating user's input
 */
public class EchoCommand extends Command {
    public EchoCommand(String command) {
        super(command);
    }

    /**
     * Executes EchoCommand, i.e., Repeats user's input.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object of Damon object.
     * @param storage Storage object of Damon object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showEcho(this.command);
    }
}
