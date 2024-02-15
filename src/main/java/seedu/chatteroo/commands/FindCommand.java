package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;

/**
 * Finds the tasks that contain the specified keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for the FindCommand class.
     * @param keyword The keyword to be searched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the specified command by finding the tasks that contain the keyword.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.findTasks(keyword);
        int listCount = filteredTasks.getTaskListSize();
        String findTaskUi = ui.showFindTaskText(listCount);
        if (listCount == 0) {
            return findTaskUi;
        }
        return findTaskUi + filteredTasks.getTaskListString();

    }
}
