package shon.command;

import shon.TaskList;
import shon.Ui;

/**
 * Represents a find command to show tasks with description containing keyword.
 */
public class FindCommand extends Command {
    /** The keyword to look for in task descriptions */
    private String keyword;

    /**
     * Creates a new command to find tasks with description containing keyword.
     *
     * @param keyword The keyword to look for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Outputs the tasks with description containing the keyword.
     *
     * @param tasks The <code>TaskList</code> containing all the tasks to look through.
     * @param ui The <code>Ui</code> used to output the result of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.print(tasks.filterByKeyword(this.keyword));
    }
}
