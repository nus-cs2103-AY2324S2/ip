package chatteroo.commands;

import chatteroo.ChatterooException;
import chatteroo.tasks.TaskList;
import chatteroo.ui.Ui;
import chatteroo.storage.Storage;

/**
 * Clears the tasks that are marked as done.
 */
public class DoneCommand extends Command {
    /**
     * Constructor for the DoneCommand class.
     */
    public DoneCommand() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatterooException {
        tasks.clearDoneTasks();
        return ui.showClearDoneTaskResponse();
    }
}
