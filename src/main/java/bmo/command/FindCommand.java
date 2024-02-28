package bmo.command;

import bmo.task.Task;
import bmo.ui.Ui;
import bmo.util.Storage;
import bmo.util.TaskList;

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
        output.append("----------------------------------------------------------------------------------");
        for (Task currTask : tasks) {
            if (currTask.toString().contains(keyword)) {
                tasksFound.append(idx_counter).append(". ").append(currTask.getStatusIcon()).append(" ").append(currTask).append("\n");
            }
            idx_counter++;
        }

        if (tasksFound.length() == 0) {
            output.append("BMO could not find any tasks with the keyword: ").append(keyword).append("\n");
        } else {
            output.append("BMO has found the following tasks with the keyword: ").append(keyword).append("\n");
            output.append(tasksFound);
        }

        output.append("----------------------------------------------------------------------------------");

        return output.toString();
    }
}
