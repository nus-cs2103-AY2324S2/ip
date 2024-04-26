package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;

/**
 * The ByeCommand class represents a command to end the interaction with Dibo.
 */
public class ByeCommand extends Command {

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) {
        ui.sayBye();
    }

    /**
     * Returns a boolean false to signal that the interaction with Dibo has ended.
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
