package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * Represents an action to list the tasks matching a specified keyword.
 * A <code>ListKeywordCommand</code> object corresponds to a command to list all tasks currently in the task list
 * that have a specified keyword.
 */
public class ListKeywordCommand extends ListCommand {
    private final String keyword;

    /**
     * Returns a command to list the tasks matching a specified keyword.
     *
     * @param keyword The keyword which the listed tasks are to match.
     */
    public ListKeywordCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to list the tasks matching a specified keyword.
     *
     * @param ui The UI to display the list.
     * @param storage The storage to retrieve the tasks to list.
     * @param taskList The task list that stores the tasks that might be listed.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.showFind(taskList.listKeyword(keyword));
    }
}
