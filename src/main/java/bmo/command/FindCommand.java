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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();
        int idx_counter = 1;
        for (Task currTask : tasks) {
            if (currTask.toString().contains(keyword)) {
                output.append(idx_counter).append(". ").append(currTask.getStatusIcon()).append(" ").append(currTask).append("\n");
            }
            idx_counter++;
        }
        ui.showLine();
        System.out.println(output.toString());
        ui.showLine();
    }
}
