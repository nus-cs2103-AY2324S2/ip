package shon.command.task;

import shon.task.TaskList;

/**
 * Represents a find command to show tasks with description containing keyword.
 */
public class FindCommand extends TaskCommand {
    /** The keyword to look for in task descriptions */
    private String keyword;

    /**
     * Creates a new command to find tasks with description containing keyword.
     *
     * @param tasks The TaskList associated with this command.
     * @param keyword The keyword to look for in task descriptions.
     */
    public FindCommand(TaskList tasks, String keyword) {
        super(tasks);
        this.keyword = keyword;
    }

    /**
     * Outputs the tasks with description containing the keyword.
     *
     */
    @Override
    public String execute() {
        return String.join("\n", this.tasks.filterByKeyword(this.keyword));
    }
}
