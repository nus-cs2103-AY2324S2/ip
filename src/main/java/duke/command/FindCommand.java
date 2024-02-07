package duke.command;

import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

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
     * @param ui Duke Ui.
     * @param storage Duke Storage.
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showFind(this.keyword);
        ui.listTasks(list.searchList(this.keyword));
    }

    public boolean isExit() {
        return false;
    }
}
