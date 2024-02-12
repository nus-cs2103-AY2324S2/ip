package aurora.command;

import aurora.storage.Storage;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

/**
 * The ByeCommand class handles the "bye" command.
 */
public class ByeCommand extends Command {

    /** TaskList to interact with. */
    private TaskList taskList;

    /** Ui to interact with. */
    private Ui ui;

    /** Storage to interact with. */
    private Storage storage;

    /**
     * Constructor for the ByeCommand class.
     *
     * @param taskList TaskList to edit.
     * @param ui Ui to interact with.
     * @param storage Storage to interact with.
     */
    public ByeCommand(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    public String handle() {
        return this.ui.getExitMessage();
    }

    @Override
    public boolean isBye() {
        return true;
    }

}
