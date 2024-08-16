package gmo.command;

import gmo.ui.Ui;
import gmo.util.Storage;
import gmo.util.TaskList;


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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printTutorial();
    }
}
