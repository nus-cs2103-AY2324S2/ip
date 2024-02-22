package chatteroo.commands;

import chatteroo.tasks.TaskList;
import chatteroo.ui.Ui;
import chatteroo.storage.Storage;

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
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.findTasks(keyword);
        int listCount = filteredTasks.getTaskListSize();
        String findTaskUi = ui.showFindTaskResponse(listCount);
        if (listCount == 0) {
            return findTaskUi;
        }
        return findTaskUi + filteredTasks.getTaskListString();

    }
}
