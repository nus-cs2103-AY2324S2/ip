package lrbg.codriver.command;

import lrbg.codriver.data.Task;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.data.Todo;
import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

/**
 * Represents a command to find tasks in the task list that match a keyword.
 */
public class FindCommand extends Command {
    /** The keyword to search for. */
    private final String keyword;

    /**
     * Creates a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        TaskList matchingTasks = tasks.findTasks(this.keyword);
        return ui.showMatchingTasks(matchingTasks);
    }

    /**
     * Returns true if the given object is equal to this command, only for testing purposes.
     * @param obj The object to compare to.
     * @return True if the given object contains the same keyword, false otherwise.
     */
    public boolean testEquals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof FindCommand) {
            FindCommand other = (FindCommand) obj;
            return other.keyword.equals(this.keyword);
        } else {
            return false;
        }
    }
}
