package command;

import exception.BuddyException;
import storage.Storage;
import task.TaskList;

/**
 * Represents Command that exits buddy.Buddy.
 */
public class ExitCommand extends Command {

    /**
     * Saves data from TaskList into storage file.
     *
     * @param tasks TaskList consisting of Tasks.
     * @param storage Current Storage object used.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            storage.save(tasks);
            return "See ya!";
        } catch (BuddyException e) {
            return "Error while saving to file!";
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
