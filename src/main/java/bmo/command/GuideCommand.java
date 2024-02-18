package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;


/**
 * Represents a guide command to be executed.
 */
public class GuideCommand extends Command {

    /**
     * Executes the guide command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTutorial();
    }
}
