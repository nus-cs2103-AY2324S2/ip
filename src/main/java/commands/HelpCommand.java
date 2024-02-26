package commands;

import exceptions.LeluException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class HelpCommand extends Command {
    /**
     * Returns instructions of valid user inputs.
     *
     * @param tasks Recorded list of tasks.
     * @param ui Format of output shown.
     * @param storage To save and load tasks recorded.
     * @param message Input of user.
     * @return
     * @throws LeluException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        return ui.showInstructions();
    }
}
