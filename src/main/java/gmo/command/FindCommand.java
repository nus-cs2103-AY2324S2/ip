package gmo.command;

import gmo.task.Task;
import gmo.ui.Ui;
import gmo.util.Storage;
import gmo.util.TaskList;

/**
 * Represents a find command to be executed.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for a find command.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();
        StringBuilder tasksFound = new StringBuilder();
        int idx_counter = 1;
        for (Task currTask : tasks) {
            if (currTask.toString().contains(keyword)) {
                tasksFound.append(idx_counter).append(". ").append(currTask.getStatusIcon()).append(" ").append(currTask).append("\n");
            }
            idx_counter++;
        }

        if (tasksFound.length() == 0) {
            output.append("GMO could not find any tasks with the keyword: ").append(keyword).append("\n");
        } else {
            output.append("GMO has found the following tasks with the keyword: ").append(keyword).append("\n");
            output.append(tasksFound);
        }

        return output.toString();
    }
}
