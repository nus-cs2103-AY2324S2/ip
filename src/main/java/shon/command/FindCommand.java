package shon.command;

import shon.TaskList;

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
     */
    @Override
    public String execute(TaskList tasks) {
        return String.join("\n", tasks.filterByKeyword(this.keyword));
    }
}
