package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.clearDoneTasks();
        return ui.showClearDoneTaskResponse();
    }
}
