package chad.command;

import chad.utility.Storage;
import chad.utility.TaskList;
import chad.utility.Ui;

/**
 * Represents a command to find a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds a task with a given keyword.
     *
     * @param list the task list.
     * @param ui Chad Ui.
     * @param storage Chad Storage.
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showFind(this.keyword);
        ui.listTasks(list.searchList(this.keyword));
    }

    public boolean isExit() {
        return false;
    }
}
