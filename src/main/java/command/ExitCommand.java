package command;

import exception.BuddyException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents Command that exits Buddy.
 */
public class ExitCommand extends Command {

    /**
     * Saves data from TaskList into storage file.
     *
     * @param tasks TaskList consisting of Tasks.
     * @param ui Current Ui object used.
     * @param storage Current Storage object used.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
            ui.showBye();
        } catch (BuddyException e) {
            ui.showSavingError();
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
